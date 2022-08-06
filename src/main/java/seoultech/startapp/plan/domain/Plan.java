package seoultech.startapp.plan.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class Plan {

    private Long planId;

    private String planName;

    private String color;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Builder
    public Plan(Long planId, String planName, String color, LocalDateTime startTime, LocalDateTime endTime) {
        this.planId = planId;
        this.planName = planName;
        this.color = color;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
