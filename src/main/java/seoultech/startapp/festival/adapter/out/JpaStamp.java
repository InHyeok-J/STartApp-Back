package seoultech.startapp.festival.adapter.out;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "stamp")
@Getter
@NoArgsConstructor
public class JpaStamp {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "stamp_id")
  private Long id;

  @Column(name = "member_id", unique = true)
  private Long memberId;

  private Boolean exhibition;
  private Boolean ground;
  private Boolean fleamarket;
  private Boolean bungeobang;
  private Boolean sangsang;

  @Builder
  public JpaStamp(Long id, Long memberId, Boolean exhibition, Boolean ground,
      Boolean fleamarket, Boolean bungeobang, Boolean sangsang) {
    this.id = id;
    this.memberId = memberId;
    this.exhibition = exhibition;
    this.ground = ground;
    this.fleamarket = fleamarket;
    this.bungeobang = bungeobang;
    this.sangsang = sangsang;
  }
}
