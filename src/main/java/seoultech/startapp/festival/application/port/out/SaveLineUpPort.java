package seoultech.startapp.festival.application.port.out;

import seoultech.startapp.festival.domain.LineUp;

public interface SaveLineUpPort {

  void save(LineUp lineUp);
}
