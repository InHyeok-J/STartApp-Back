package seoultech.startapp.member.application.port.in;

import org.springframework.stereotype.Component;
import seoultech.startapp.member.application.AccessToken;

@Component
public interface RefreshUseCase {
  AccessToken refresh(RefreshCommand command);
}
