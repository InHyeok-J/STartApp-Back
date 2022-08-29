package seoultech.startapp.member.application.port.in;

import seoultech.startapp.member.application.port.in.command.MemberPasswordChangeCommand;
import seoultech.startapp.member.application.port.in.command.NotLoginPasswordChangeCommand;

public interface PasswordUseCase {

  void notLoginPasswordChange(NotLoginPasswordChangeCommand command);

  void memberPasswordChange(MemberPasswordChangeCommand command);
}
