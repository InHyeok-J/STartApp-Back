package seoultech.startapp.member.application.port.in;

import org.springframework.stereotype.Component;
import seoultech.startapp.member.application.port.in.command.LogoutCommand;

@Component
public interface LogoutUseCase {
  void logout(LogoutCommand command);
}
