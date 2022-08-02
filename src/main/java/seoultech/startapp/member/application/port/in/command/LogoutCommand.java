package seoultech.startapp.member.application.port.in.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import seoultech.startapp.global.common.SelfValidator;

@Getter
@EqualsAndHashCode(callSuper = false)
public class LogoutCommand extends SelfValidator<LogoutCommand> {

  @NotNull
  private Long memberId;

  @NotBlank
  private String refreshToken;

  public LogoutCommand(Long memberId, String refreshToken) {
    this.memberId = memberId;
    this.refreshToken = refreshToken;
    this.validateSelf();
  }
}
