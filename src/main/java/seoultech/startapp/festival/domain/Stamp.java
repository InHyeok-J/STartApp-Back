package seoultech.startapp.festival.domain;

import lombok.Builder;
import lombok.Getter;
import seoultech.startapp.festival.exception.AlreadyStampException;

@Getter
public class Stamp {

  private Long stampId;
  private Long memberId;
  private Boolean exhibition;
  private Boolean ground;
  private Boolean fleamarket;
  private Boolean bungeobang;
  private Boolean sangsang;

  @Builder
  public Stamp(Long stampId, Long memberId, Boolean exhibition, Boolean ground,
      Boolean fleamarket, Boolean bungeobang, Boolean sangsang) {
    this.stampId = stampId;
    this.memberId = memberId;
    this.exhibition = exhibition;
    this.ground = ground;
    this.fleamarket = fleamarket;
    this.bungeobang = bungeobang;
    this.sangsang = sangsang;
  }

  public void addStamp(StampList target) {
    switch (target) {
      case EXHIBITION -> setExhibition(true);
      case GROUND -> setGround(true);
      case FLEAMARKET -> setFleamarket(true);
      case BUNGEOBANG -> setBungeobang(true);
      case SANGSANG -> setSangsang(true);
    }
  }

  public static Stamp initStamp(Long memberId) {
    return Stamp.builder()
        .memberId(memberId)
        .exhibition(false)
        .fleamarket(false)
        .ground(false)
        .bungeobang(false)
        .sangsang(false)
        .build();
  }

  private void validationStamp(Boolean target) {
    if (target) {
      throw new AlreadyStampException("이미 찍은 스탬프입니다.");
    }
  }

  private void setExhibition(Boolean exhibition) {
    validationStamp(this.exhibition);
    this.exhibition = exhibition;
  }

  private void setGround(Boolean ground) {
    validationStamp(this.ground);
    this.ground = ground;
  }

  private void setFleamarket(Boolean fleamarket) {
    validationStamp(this.fleamarket);
    this.fleamarket = fleamarket;
  }

  private void setBungeobang(Boolean bungeobang) {
    validationStamp(this.bungeobang);
    this.bungeobang = bungeobang;
  }

  private void setSangsang(Boolean sangsang) {
    validationStamp(this.sangsang);
    this.sangsang = sangsang;
  }
}
