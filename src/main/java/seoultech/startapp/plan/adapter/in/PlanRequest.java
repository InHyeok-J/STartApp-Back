package seoultech.startapp.plan.adapter.in;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
class PlanRequest {


    private String planName;

    private String color;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}
