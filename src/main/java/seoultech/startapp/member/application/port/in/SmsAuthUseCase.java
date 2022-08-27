package seoultech.startapp.member.application.port.in;

import seoultech.startapp.member.application.port.in.command.SmsCheckCommand;
import seoultech.startapp.member.application.port.in.command.SmsPushCommand;

public interface SmsAuthUseCase {

  void push(SmsPushCommand command);
  void check(SmsCheckCommand command);
}
