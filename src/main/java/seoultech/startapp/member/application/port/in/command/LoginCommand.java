package seoultech.startapp.member.application.port.in.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import seoultech.startapp.global.common.SelfValidator;

@Getter
@EqualsAndHashCode(callSuper = false)
public class LoginCommand extends SelfValidator<LoginCommand> {

  @NotBlank
  private final String studentNo;

  @NotBlank
  @Pattern(regexp = "[a-zA-Z0-9~!@#$%^&*?<>,.+=]{8,16}")
  private final String password;

  public LoginCommand(String studentNo, String password) {
    this.studentNo = studentNo;
    this.password = password;
    this.validateSelf();
  }
}
