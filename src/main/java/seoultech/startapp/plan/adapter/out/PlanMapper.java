package seoultech.startapp.plan.adapter.out;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import seoultech.startapp.plan.domain.Plan;

import java.util.List;
import java.util.stream.Collectors;

@Component
class PlanMapper {

    public Plan mapToDomainPlan(JpaPlan jpaPlan){
        return Plan.builder()
            .planId(jpaPlan.getId())
            .planName(jpaPlan.getPlanName())
            .color(jpaPlan.getColor())
            .startTime(jpaPlan.getStartTime())
            .endTime(jpaPlan.getEndTime())
            .build();
    }

    public JpaPlan mapToJpaPlan(Plan plan){
        return JpaPlan.builder()
            .id(plan.getPlanId() == null ? null : plan.getPlanId())
            .planName(plan.getPlanName())
            .color(plan.getColor())
            .startTime(plan.getStartTime())
            .endTime(plan.getEndTime())
            .build();
    }

    public Page<Plan> mapToDomainPlanPage(Page<JpaPlan> jpaPlanPage){
        return jpaPlanPage.map(this::mapToDomainPlan);
    }

    public List<Plan> mapToDomainPlanList(List<JpaPlan> jpaPlans){
        return jpaPlans.stream().map(this::mapToDomainPlan)
            .collect(Collectors.toList());
    }
}
