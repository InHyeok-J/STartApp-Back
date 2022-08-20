package seoultech.startapp.festival.adapter.out;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "booth")
@Getter
@NoArgsConstructor
public class JpaBooth {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "booth_id")
  private Long id;

  @Column
  private String name;

  @Column
  private int congestion = 1;

  @Builder
  public JpaBooth(Long id, String name, int congestion) {
    this.id = id;
    this.name = name;
    this.congestion = congestion;
  }
}
