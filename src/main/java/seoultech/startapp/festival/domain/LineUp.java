package seoultech.startapp.festival.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LineUp {

  private Long lineUpId;

  private String lineUpTitle;

  private LocalDate lineUpDay;

  private LocalDateTime lineUpTime;

  @Builder
  public LineUp(Long lineUpId, String lineUpTitle, LocalDate lineUpDay,
      LocalDateTime lineUpTime) {
    this.lineUpId = lineUpId;
    this.lineUpTitle = lineUpTitle;
    this.lineUpDay = lineUpDay;
    this.lineUpTime = lineUpTime;
  }
}
