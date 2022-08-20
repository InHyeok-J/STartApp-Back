package seoultech.startapp.festival.application.port.out;

import seoultech.startapp.festival.domain.Booth;

public interface SaveBoothPort {

  void save(Booth booth);
}
