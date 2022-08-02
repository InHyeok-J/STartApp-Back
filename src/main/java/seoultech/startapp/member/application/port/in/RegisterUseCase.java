package seoultech.startapp.member.application.port.in;

import seoultech.startapp.member.application.AllToken;
import seoultech.startapp.member.application.port.in.command.RegisterCommand;

public interface RegisterUseCase {

  AllToken register(RegisterCommand command);
}
