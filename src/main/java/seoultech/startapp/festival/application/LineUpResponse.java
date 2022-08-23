package seoultech.startapp.festival.application;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import seoultech.startapp.festival.domain.LineUp;

@Getter
@NoArgsConstructor
public class LineUpResponse {

  private Long lineUpId;

  private String lineUpTitle;

  private LocalDate lineUpDay;

  private LocalDateTime lineUpTime;

  @Builder
  public LineUpResponse(Long lineUpId, String lineUpTitle, LocalDate lineUpDay,
      LocalDateTime lineUpTime) {
    this.lineUpId = lineUpId;
    this.lineUpTitle = lineUpTitle;
    this.lineUpDay = lineUpDay;
    this.lineUpTime = lineUpTime;
  }

  public static LineUpResponse toDto(LineUp lineUp){
    return LineUpResponse.builder()
        .lineUpId(lineUp.getLineUpId())
        .lineUpTitle(lineUp.getLineUpTitle())
        .lineUpTime(lineUp.getLineUpTime())
        .lineUpDay(lineUp.getLineUpDay())
        .build();
  }
}
