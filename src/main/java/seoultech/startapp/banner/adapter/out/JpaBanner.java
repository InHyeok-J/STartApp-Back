package seoultech.startapp.banner.adapter.out;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import seoultech.startapp.global.common.BaseTimeJpaEntity;

@Getter
@NoArgsConstructor
@Entity(name = "banner")
public class JpaBanner extends BaseTimeJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "banner_id")
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String imageUrl;

  @Column(nullable = false)
  private int priority;

  @Column(nullable = false)
  private Boolean isDeleted;

  @Builder
  public JpaBanner(Long id, String title, String imageUrl, int priority, Boolean isDeleted) {
    this.id = id;
    this.title = title;
    this.imageUrl = imageUrl;
    this.priority = priority;
    this.isDeleted = isDeleted;
  }
}
