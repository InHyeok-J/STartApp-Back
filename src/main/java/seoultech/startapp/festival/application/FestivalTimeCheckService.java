package seoultech.startapp.festival.application;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import seoultech.startapp.festival.application.port.in.FestivalTimeCheckUseCase;
import seoultech.startapp.festival.exception.NotStartFestivalException;

@Component
@RequiredArgsConstructor
public class FestivalTimeCheckService implements FestivalTimeCheckUseCase {

  private final FestivalTimeChecker festivalTimeChecker;

  @Value("${spring.profiles.active}")
  private String profile;

  @Override
  public void check() {
    if(!profile.equals("prod")){
      return;
    }

    if(!festivalTimeChecker.isStart(LocalDateTime.now())){
      // 시작 시간이 아니면
      throw new NotStartFestivalException("축제 시작 시간이 아닙니다.");
    }
  }
}
