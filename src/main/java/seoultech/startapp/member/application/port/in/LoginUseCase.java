package seoultech.startapp.member.application.port.in;

import org.springframework.stereotype.Component;
import seoultech.startapp.member.domain.AllToken;

@Component
public interface LoginUseCase {
  AllToken login(LoginCommand command);
}
