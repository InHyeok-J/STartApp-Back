package seoultech.startapp.plan.adapter.out;

import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import seoultech.startapp.plan.domain.Plan;

import java.time.LocalDateTime;
import java.util.List;

import static seoultech.startapp.plan.adapter.out.QJpaPlan.jpaPlan;

@Repository
@RequiredArgsConstructor
public class JpaPlanQueryRepository {

    private final JPAQueryFactory query;

    public List<Plan> findPlanByYearAndMonth(LocalDateTime startTime,LocalDateTime endTime){

        return query.select(selectPlanInfo())
            .from(jpaPlan)
            .where(findPlanByStartAndEnd(startTime,endTime))
            .fetch();
    }

    public ConstructorExpression<Plan> selectPlanInfo(){
        return Projections.constructor(Plan.class,
                                       jpaPlan.id,
                                       jpaPlan.planName,
                                       jpaPlan.color,
                                       jpaPlan.startTime,
                                       jpaPlan.endTime,
                                       jpaPlan.createdAt,
                                       jpaPlan.updatedAt);
    }

    private BooleanExpression findPlanByStartAndEnd(LocalDateTime startTime, LocalDateTime endTime){
        return jpaPlan.startTime.between(startTime,endTime).or(jpaPlan.endTime.between(startTime,endTime));
    }

}
