package seoultech.startapp.plan.application.port.in;

import lombok.Builder;
import lombok.Getter;
import seoultech.startapp.global.common.SelfValidator;
import seoultech.startapp.plan.domain.Plan;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@Getter
public class PlanCommand extends SelfValidator<PlanCommand> {

    @NotBlank
    @Size(max = 255)
    private String planName;

    @NotBlank
    @Pattern(regexp = "^#[0-9A-F]{8}")
    private String color;

    @NotNull
    private LocalDateTime startTime;

    @NotNull
    private LocalDateTime endTime;

    @Builder
    public PlanCommand(String planName, String color, LocalDateTime startTime, LocalDateTime endTime) {
        this.planName = planName;
        this.color = color;
        this.startTime = startTime;
        this.endTime = endTime;
        this.validateSelf();
    }

    public static Plan ToDomainPlan(PlanCommand planCommand){
        return Plan.builder()
                   .planName(planCommand.getPlanName())
                   .color(planCommand.getPlanName())
                   .startTime(planCommand.getStartTime())
                   .endTime(planCommand.getEndTime())
                   .build();
    }
}
