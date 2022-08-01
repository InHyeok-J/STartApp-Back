package seoultech.startapp.member.application.port.in;

import org.springframework.stereotype.Component;
import seoultech.startapp.member.application.AllToken;
import seoultech.startapp.member.application.port.in.command.LoginCommand;

@Component
public interface LoginUseCase {
  AllToken login(LoginCommand command);
}
