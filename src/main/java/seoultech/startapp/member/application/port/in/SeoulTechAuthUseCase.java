package seoultech.startapp.member.application.port.in;

import seoultech.startapp.member.application.SeoulTechAuthResponse;
import seoultech.startapp.member.application.port.in.command.SeoulTechAuthCommand;

public interface SeoulTechAuthUseCase {

  void authSeoulTech(SeoulTechAuthCommand command);
  SeoulTechAuthResponse checkAuth(String key);
}
