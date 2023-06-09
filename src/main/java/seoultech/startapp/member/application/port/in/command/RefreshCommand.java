package seoultech.startapp.member.application.port.in.command;

import javax.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import seoultech.startapp.global.common.SelfValidator;

@Getter
@EqualsAndHashCode(callSuper = false)
public class RefreshCommand  extends SelfValidator<RefreshCommand>  {

  @NotBlank
  private final String accessToken;

  @NotBlank
  private final String refreshToken;

  public RefreshCommand(String accessToken, String refreshToken) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
    this.validateSelf();
  }
}
