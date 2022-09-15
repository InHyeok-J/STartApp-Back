package seoultech.startapp.member.application.port.in.command;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import seoultech.startapp.global.common.SelfValidator;

@Getter
public class StudentCardAuthCommand extends SelfValidator<StudentCardAuthCommand> {

  @NotNull
  private Long memberId;

  @NotNull
  private boolean isAuth;

  @Builder
  public StudentCardAuthCommand(Long memberId, boolean isAuth) {
    this.memberId = memberId;
    this.isAuth = isAuth;
  }
}
