package seoultech.startapp.member.application.port.in;

import org.springframework.stereotype.Component;

@Component
public interface LogoutUseCase {
  void logout(LogoutCommand command);
}
