package seoultech.startapp.festival.application.port.in;

import seoultech.startapp.festival.application.StampResponse;
import seoultech.startapp.festival.application.port.in.command.StampCommand;

public interface StampUseCase {

  void stamp(StampCommand command);
  StampResponse getMyStamp(Long memberId);
}
