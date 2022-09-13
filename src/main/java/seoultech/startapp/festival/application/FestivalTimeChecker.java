package seoultech.startapp.festival.application;

import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class FestivalTimeChecker {

  private final LocalDateTime FESTIVAL_START_TIME = LocalDateTime.of(2022, 9, 21, 9, 0);
  private final LocalDateTime FESTIVAL_END_TIME = LocalDateTime.of(2022,9,24,0,0);

  public boolean isStart(LocalDateTime current) {
    if (current.isBefore(FESTIVAL_START_TIME)) {
      return false;
    } else {
      return true;
    }
  }

}
