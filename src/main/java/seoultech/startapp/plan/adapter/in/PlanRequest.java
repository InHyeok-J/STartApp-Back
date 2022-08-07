package seoultech.startapp.plan.adapter.in;

import lombok.Getter;
import seoultech.startapp.plan.application.port.in.PlanCommand;

import java.time.LocalDateTime;

@Getter
class PlanRequest {


    private String planName;

    private String color;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    public PlanCommand ToPlanCommand(){
        return PlanCommand.builder()
            .planName(this.planName)
            .color(this.color)
            .startTime(this.startTime)
            .endTime(this.endTime)
            .build();
    }
}
