package seoultech.startapp.member.application.port.in;

import seoultech.startapp.member.application.port.in.command.StudentCardAuthCommand;

public interface StudentCardAuthUseCase {

  void cardAuth(StudentCardAuthCommand command);
}
