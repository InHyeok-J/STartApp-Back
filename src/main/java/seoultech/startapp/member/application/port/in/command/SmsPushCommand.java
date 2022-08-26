package seoultech.startapp.member.application.port.in.command;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import seoultech.startapp.global.common.SelfValidator;
import seoultech.startapp.member.domain.SmsAuth;

@Getter
public class SmsPushCommand extends SelfValidator<SmsPushCommand> {

  @NotNull
  @Pattern(regexp = "01([0|1|6|7|8|9])-([0-9]{3,4})-([0-9]{4})")
  private String phoneNo;

  public SmsPushCommand(String phoneNo) {
    this.phoneNo = phoneNo;
    validateSelf();
  }

  public SmsAuth toEntity(String code){
    return SmsAuth.builder()
        .phoneNo(phoneNo)
        .authCode(code)
        .smsTime(LocalDateTime.now())
        .build();
  }
}
