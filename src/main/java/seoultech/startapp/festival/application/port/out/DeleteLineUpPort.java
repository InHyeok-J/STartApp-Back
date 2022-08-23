package seoultech.startapp.festival.application.port.out;

import seoultech.startapp.festival.domain.LineUp;

public interface DeleteLineUpPort {

  void deleteLineUp(LineUp lineUp);
}
