package seoultech.startapp.festival.adapter.out;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import seoultech.startapp.global.converter.BooleanToYNConverter;

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

  @Column(nullable = false)
  @Convert(converter = BooleanToYNConverter.class)
  private Boolean exhibition;

  @Column(nullable = false)
  @Convert(converter = BooleanToYNConverter.class)
  private Boolean ground;

  @Column(nullable = false)
  @Convert(converter = BooleanToYNConverter.class)
  private Boolean fleamarket;

  @Column(nullable = false)
  @Convert(converter = BooleanToYNConverter.class)
  private Boolean bungeobang;

  @Column(nullable = false)
  @Convert(converter = BooleanToYNConverter.class)
  private Boolean sangsang;

  @Column(nullable = false)
  @Convert(converter = BooleanToYNConverter.class)
  private Boolean isPrized;

  @Builder
  public JpaStamp(Long id, Long memberId, Boolean exhibition, Boolean ground,
      Boolean fleamarket, Boolean bungeobang, Boolean sangsang, Boolean isPrized) {
    this.id = id;
    this.memberId = memberId;
    this.exhibition = exhibition;
    this.ground = ground;
    this.fleamarket = fleamarket;
    this.bungeobang = bungeobang;
    this.sangsang = sangsang;
    this.isPrized = isPrized;
  }
}
