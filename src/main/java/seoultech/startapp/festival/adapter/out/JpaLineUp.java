package seoultech.startapp.festival.adapter.out;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "line_up")
@Getter
@NoArgsConstructor
public class JpaLineUp {
  @Id @GeneratedValue
  @Column(name = "line_up_id")
  private Long id;

  @Column(name = "line_up_day", nullable = false)
  private LocalDate lineUpDay;

  @Column(name = "line_up_time", nullable = false)
  private LocalDateTime lineUpTime;

  @Column(name = "title", nullable = false)
  private String Title;

  @Builder
  public JpaLineUp(Long id, LocalDate lineUpDay, LocalDateTime lineUpTime, String title) {
    this.id = id;
    this.lineUpDay = lineUpDay;
    this.lineUpTime = lineUpTime;
    Title = title;
  }
}
