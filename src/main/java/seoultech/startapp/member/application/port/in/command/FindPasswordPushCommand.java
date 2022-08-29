package seoultech.startapp.member.application.port.in.command;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import seoultech.startapp.global.common.SelfValidator;

@Getter
public class FindPasswordPushCommand extends SelfValidator<FindPasswordPushCommand> {

  @NotBlank
  private String studentNo;

  public FindPasswordPushCommand(String studentNo) {
    this.studentNo = studentNo;
    validateSelf();
  }
}
