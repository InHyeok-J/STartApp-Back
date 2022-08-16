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

import static seoultech.startapp.rent.adapter.out.QJpaItem.jpaItem;

@Repository
@RequiredArgsConstructor
public class JpaItemQueryRepository {

    private final JPAQueryFactory query;

    public Page<JpaItem> findAllByOrderByItemNoAsc(Pageable pageable, ItemCategory itemCategory){
        QueryResults<JpaItem> results = query.selectFrom(jpaItem)
                                                         .where(rentStatusEq(itemCategory))
                                                         .orderBy(jpaItem.itemNo.asc())
                                                         .offset(pageable.getOffset())
                                                         .limit(pageable.getPageSize())
                                                         .fetchResults();
        return new PageImpl<>(results.getResults(),pageable,results.getTotal());
    }

    private BooleanExpression rentStatusEq(ItemCategory itemCategory) {
        return itemCategory == ItemCategory.ALL ? null : jpaItem.itemCategory.eq(itemCategory);
    }
}
