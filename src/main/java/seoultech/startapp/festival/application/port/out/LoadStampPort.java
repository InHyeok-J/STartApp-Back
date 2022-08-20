package seoultech.startapp.festival.application.port.out;

import seoultech.startapp.festival.domain.Stamp;

public interface LoadStampPort {

  Stamp loadByMemberId(Long memberId);
}
