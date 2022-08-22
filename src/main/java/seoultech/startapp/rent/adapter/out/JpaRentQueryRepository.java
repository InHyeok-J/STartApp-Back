package seoultech.startapp.rent.adapter.out;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import seoultech.startapp.rent.domain.ItemCategory;
import seoultech.startapp.rent.domain.RentStatus;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;
import static seoultech.startapp.rent.adapter.out.QJpaRent.jpaRent;
import static seoultech.startapp.rent.domain.RentStatus.CONFIRM;
import static seoultech.startapp.rent.domain.RentStatus.NOT_RETURN;
import static seoultech.startapp.rent.domain.RentStatus.RENT;
import static seoultech.startapp.rent.domain.RentStatus.WAIT;

@Repository
@RequiredArgsConstructor
class JpaRentQueryRepository {

    private final JPAQueryFactory query;

    public long countIncludingStartTime(LocalDate startTime, ItemCategory itemCategory){
        Integer totalCount = query.select(jpaRent.account.sum())
                               .from(jpaRent)
                               .where(findStartTimeBetweenStartAndEnd(startTime))
                               .where(itemCategoryEq(itemCategory))
                               .where(rentStatusEq())
                               .fetchOne();
        return totalCount == null ? 0 : totalCount;
    }
    public long countIncludingEndTime(LocalDate endTime, ItemCategory itemCategory){
        Integer totalCount = query.select(jpaRent.account.sum())
                               .from(jpaRent)
                               .where(findEndTimeBetweenStartAndEnd(endTime))
                               .where(itemCategoryEq(itemCategory))
                               .where(rentStatusEq())
                               .fetchOne();
        return totalCount == null ? 0 : totalCount;
    }

    private BooleanExpression itemCategoryEq(ItemCategory itemCategory){
        return jpaRent.itemCategory.eq(itemCategory);
    }
    private BooleanExpression findEndTimeBetweenStartAndEnd(LocalDate endTime){
        return jpaRent.startTime.loe(endTime).and(jpaRent.endTime.goe(endTime));
    }

    private BooleanExpression findStartTimeBetweenStartAndEnd(LocalDate starTime){
        return jpaRent.startTime.loe(starTime).and(jpaRent.endTime.goe(starTime));
    }
    private BooleanExpression rentStatusEq(){
        return jpaRent.rentStatus.in(asList(RENT, CONFIRM, NOT_RETURN, WAIT));
    }


    public Page<JpaRent> findAllByRentStatusOrderByStartTimeDesc(Pageable pageable, RentStatus rentStatus){
        QueryResults<JpaRent> result = query.selectFrom(jpaRent)
                                                         .where(rentStatusEq(rentStatus))
                                                         .orderBy(jpaRent.startTime.desc())
                                                         .offset(pageable.getOffset())
                                                         .limit(pageable.getPageSize())
                                                         .fetchResults();

        return new PageImpl<>(result.getResults(),pageable,result.getTotal());
    }

    private BooleanExpression rentStatusEq(RentStatus rentStatus){
        return rentStatus == RentStatus.ALL ? null : jpaRent.rentStatus.eq(rentStatus);
    }

    public List<JpaRent> findAllByYearAndMonthAndItemCategory(LocalDate monthStartTime,LocalDate monthEndTime, ItemCategory itemCategory){
        List<JpaRent> jpaRents = query.selectFrom(jpaRent)
                                       .where(getRentByMonth(monthStartTime, monthEndTime),
                                              itemCategoryEq(itemCategory),
                                              rentStatusEqRenting())
                                       .orderBy(jpaRent.startTime.asc())
                                       .fetch();
        return jpaRents;
    }


    private BooleanExpression getRentByMonth(LocalDate monthStartTime,LocalDate monthEndTime){
        return startTimeBetween(monthStartTime,monthEndTime).or(endTimeBetween(monthStartTime, monthEndTime));
    }
    private BooleanExpression startTimeBetween(LocalDate monthStartTime,LocalDate monthEndTime){
        return jpaRent.startTime.goe(monthStartTime).and(jpaRent.startTime.lt(monthEndTime));
    }

    private BooleanExpression endTimeBetween(LocalDate monthStartTime,LocalDate monthEndTime){
        return jpaRent.endTime.goe(monthStartTime).and(jpaRent.startTime.lt(monthEndTime));
    }

    private BooleanExpression rentStatusEqRenting(){
        return jpaRent.rentStatus.in(WAIT, CONFIRM, RENT, NOT_RETURN);
    }
}
