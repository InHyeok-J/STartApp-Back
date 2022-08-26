package seoultech.startapp.member.application.port.in.command;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;
import seoultech.startapp.global.common.SelfValidator;

@Getter
public class SmsCheckCommand extends SelfValidator<SmsCheckCommand> {

  @NotNull
  @Pattern(regexp = "01([0|1|6|7|8|9])-([0-9]{3,4})-([0-9]{4})")
  private String phoneNo;
  @NotNull
  @Size(min = 6,max = 6)
  private String code;

  public SmsCheckCommand(String phoneNo, String code) {
    this.phoneNo = phoneNo;
    this.code = code;
    validateSelf();
  }
}
