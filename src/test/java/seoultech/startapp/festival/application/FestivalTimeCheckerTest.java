package seoultech.startapp.festival.application;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FestivalTimeCheckerTest {

  @InjectMocks
  FestivalTimeChecker festivalTimeChecker;

  @Test
  @DisplayName("현재 시간이 축제 시간 전이면 isStart가 false 리턴")
  public void festival_isStart_before() throws Exception {
    LocalDateTime notstartedTime = LocalDateTime.of(2022,9,21,3,0);
    boolean result = festivalTimeChecker.isStart(notstartedTime);
    assertFalse(result);
  }

  @Test
  @DisplayName("현재 시간이 축제 이후면 isStart가 true 리턴")
  public void festival_isStart_after() throws Exception {
    LocalDateTime afterTime = LocalDateTime.of(2022,9,21,10,0);
    boolean result = festivalTimeChecker.isStart(afterTime);
    assertTrue(result);
  }
}