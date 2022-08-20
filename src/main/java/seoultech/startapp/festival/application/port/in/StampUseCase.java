package seoultech.startapp.festival.application.port.in;

import seoultech.startapp.festival.application.StampResponse;

public interface StampUseCase {

  void stamp(StampCommand command);
  StampResponse getMyStamp(Long memberId);
}
