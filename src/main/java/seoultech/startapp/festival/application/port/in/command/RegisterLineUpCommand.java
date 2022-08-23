package seoultech.startapp.festival.application.port.in.command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import seoultech.startapp.festival.domain.LineUp;
import seoultech.startapp.global.common.SelfValidator;

@Getter
@EqualsAndHashCode(callSuper = false)
public class RegisterLineUpCommand extends SelfValidator<RegisterLineUpCommand> {

  @NotBlank
  private String title;

  @NotNull
  private LocalDate lineUpDay;

  @NotNull
  private LocalDateTime lineUpTime;

  @Builder
  public RegisterLineUpCommand(String title, LocalDate lineUpDay, LocalDateTime lineUpTime) {
    this.title = title;
    this.lineUpDay = lineUpDay;
    this.lineUpTime = lineUpTime;
    validateSelf();
  }

  public LineUp toDomainLineUp(){
    return LineUp.builder()
        .lineUpTitle(title)
        .lineUpDay(lineUpDay)
        .lineUpTime(lineUpTime)
        .build();
  }
}
