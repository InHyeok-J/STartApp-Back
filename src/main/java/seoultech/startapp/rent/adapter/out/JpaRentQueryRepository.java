package seoultech.startapp.rent.adapter.out;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import seoultech.startapp.rent.domain.ItemCategory;

import java.time.LocalDate;

import static seoultech.startapp.rent.adapter.out.QJpaRent.jpaRent;

@Repository
@RequiredArgsConstructor
class JpaRentQueryRepository {

    private final JPAQueryFactory query;

    public long countIncludingStartTime(LocalDate startTime, ItemCategory itemCategory){
        Integer totalCount = query.select(jpaRent.account.sum())
                               .from(jpaRent)
                               .where(findStartTimeBetweenStartAndEnd(startTime))
                               .where(jpaRent.itemCategory.eq(itemCategory))
                               .fetchOne();
        return totalCount == null ? 0 : totalCount;
    }

    public long countIncludingEndTime(LocalDate endTime, ItemCategory itemCategory){
        Integer totalCount = query.select(jpaRent.account.sum())
                               .from(jpaRent)
                               .where(findEndTimeBetweenStartAndEnd(endTime))
                               .where(jpaRent.itemCategory.eq(itemCategory))
                               .fetchOne();
        return totalCount == null ? 0 : totalCount;
    }


    private BooleanExpression findStartTimeBetweenStartAndEnd(LocalDate starTime){
        return jpaRent.startTime.loe(starTime).and(jpaRent.endTime.goe(starTime));
    }

    private BooleanExpression findEndTimeBetweenStartAndEnd(LocalDate endTime){
        return jpaRent.startTime.loe(endTime).and(jpaRent.endTime.goe(endTime));
    }


}
