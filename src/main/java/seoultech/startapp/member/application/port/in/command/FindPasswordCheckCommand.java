package seoultech.startapp.member.application.port.in.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import seoultech.startapp.global.common.SelfValidator;

@Getter
public class FindPasswordCheckCommand extends SelfValidator<FindPasswordCheckCommand> {

  @NotBlank
  private String studentNo;

  @NotNull
  @Size(min = 6,max = 6)
  private String code;

  public FindPasswordCheckCommand(String studentNo, String code) {
    this.studentNo = studentNo;
    this.code = code;
    validateSelf();
  }
}
