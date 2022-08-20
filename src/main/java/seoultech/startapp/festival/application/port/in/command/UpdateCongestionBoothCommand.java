package seoultech.startapp.festival.application.port.in.command;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import seoultech.startapp.global.common.SelfValidator;

@Getter
@EqualsAndHashCode(callSuper = false)
public class UpdateCongestionBoothCommand extends SelfValidator<UpdateCongestionBoothCommand> {

  @NotNull
  @Min(1)
  @Max(3)
  private int congestion;

  @NotNull
  private Long boothId;

  public UpdateCongestionBoothCommand(int congestion, Long boothId) {
    this.congestion = congestion;
    this.boothId = boothId;
    validateSelf();
  }
}
