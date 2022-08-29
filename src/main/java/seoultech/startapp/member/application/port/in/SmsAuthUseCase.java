package seoultech.startapp.member.application.port.in;

import seoultech.startapp.member.application.port.in.command.FindPasswordCheckCommand;
import seoultech.startapp.member.application.port.in.command.FindPasswordPushCommand;
import seoultech.startapp.member.application.port.in.command.SmsCheckCommand;
import seoultech.startapp.member.application.port.in.command.SmsPushCommand;

public interface SmsAuthUseCase {

  void signUpPrePush(SmsPushCommand command);
  void findPasswordPush(FindPasswordPushCommand command);
  void findPasswordCheck(FindPasswordCheckCommand command);
  void check(SmsCheckCommand command);
}
