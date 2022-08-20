package seoultech.startapp.festival.application.port.out;

import seoultech.startapp.festival.domain.Stamp;

public interface SaveStampPort {

  Stamp save(Stamp stamp);
}
