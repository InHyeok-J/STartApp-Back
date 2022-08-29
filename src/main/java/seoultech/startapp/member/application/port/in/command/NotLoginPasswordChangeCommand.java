package seoultech.startapp.member.application.port.in.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import seoultech.startapp.global.common.SelfValidator;

@Getter
public class NotLoginPasswordChangeCommand extends SelfValidator<NotLoginPasswordChangeCommand> {

  @NotBlank
  private String studentNo;
  @NotBlank
  @Pattern(regexp = "[a-zA-Z0-9~!@#$%^&*()]{8,16}")
  private String password;

  @Builder
  public NotLoginPasswordChangeCommand(String studentNo, String password) {
    this.studentNo = studentNo;
    this.password = password;
    validateSelf();
  }
}
