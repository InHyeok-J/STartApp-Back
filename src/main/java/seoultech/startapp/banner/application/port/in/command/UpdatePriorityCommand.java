package seoultech.startapp.banner.application.port.in.command;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import seoultech.startapp.global.common.SelfValidator;

@Getter
@EqualsAndHashCode(callSuper = false)
public class UpdatePriorityCommand extends SelfValidator<UpdatePriorityCommand> {

  @NotNull
  private final Long bannerId;

  @NotNull
  @Min(1)
  @Max(10)
  private final int priority;

  @Builder
  public UpdatePriorityCommand(Long bannerId, int priority) {
    this.bannerId = bannerId;
    this.priority = priority;
    this.validateSelf();
  }

  @Override
  public String toString() {
    return "UpdatePriorityCommand{" +
        "bannerId=" + bannerId +
        ", priority=" + priority +
        '}';
  }
}
