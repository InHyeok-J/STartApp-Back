package seoultech.startapp.festival.application.port.out;

import java.util.List;
import seoultech.startapp.festival.domain.LineUp;

public interface LoadLineUpPort {

  LineUp loadById(Long lineUpId);
  List<LineUp> loadListOrderByTime();
}
