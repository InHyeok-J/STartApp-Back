package seoultech.startapp.plan.application;

import lombok.Builder;
import lombok.Getter;
import seoultech.startapp.plan.domain.Plan;

import java.time.LocalDateTime;

@Getter
public class PlanResponse {

    private Long planId;

    private String planName;

    private String color;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Builder
    public PlanResponse(Long planId, String planName, String color, LocalDateTime startTime, LocalDateTime endTime) {
        this.planId = planId;
        this.planName = planName;
        this.color = color;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static PlanResponse ToPlanResponse(Plan plan){
        return PlanResponse.builder()
                           .planId(plan.getPlanId())
                           .color(plan.getColor())
                           .planName(plan.getPlanName())
                           .startTime(plan.getStartTime())
                           .endTime(plan.getEndTime())
                           .build();
    }
}
