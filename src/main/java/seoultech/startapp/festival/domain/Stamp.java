package seoultech.startapp.festival.domain;

import lombok.Builder;
import lombok.Getter;
import seoultech.startapp.festival.exception.AlreadyPrizedException;
import seoultech.startapp.festival.exception.AlreadyStampException;
import seoultech.startapp.festival.exception.RequireStampException;

@Getter
public class Stamp {

  private Long stampId;
  private Long memberId;
  private Boolean exhibition;
  private Boolean ground;
  private Boolean fleamarket;
  private Boolean bungeobang;
  private Boolean sangsang;
  private Boolean isPrized;

  @Builder
  public Stamp(Long stampId, Long memberId, Boolean exhibition, Boolean ground,
      Boolean fleamarket, Boolean bungeobang, Boolean sangsang, Boolean isPrized) {
    this.stampId = stampId;
    this.memberId = memberId;
    this.exhibition = exhibition;
    this.ground = ground;
    this.fleamarket = fleamarket;
    this.bungeobang = bungeobang;
    this.sangsang = sangsang;
    this.isPrized = isPrized;
  }

  public void addStamp(StampList target) {
    switch (target) {
      case EXHIBITION -> setExhibition(true);
      case GROUND -> setGround(true);
      case FLEAMARKET -> setFleamarket(true);
      case BUNGEOBANG -> setBungeobang(true);
      case SANGSANG -> setSangsang(true);
      case PRIZED -> setPrized(true);
    }
  }

  public void setPrized(Boolean prized) {
    if(this.isPrized){
      throw new AlreadyPrizedException("이미 상품을 받았습니다.");
    }
    this.isPrized = prized;
  }

  public static Stamp initStamp(Long memberId) {
    return Stamp.builder()
        .memberId(memberId)
        .exhibition(false)
        .fleamarket(false)
        .ground(false)
        .bungeobang(false)
        .sangsang(false)
        .isPrized(false)
        .build();
  }

  public void validationPrizedStamp() {
    validationRequireStamp(this.exhibition);
    validationRequireStamp(this.ground);
    validationRequireStamp(this.fleamarket);
    validationRequireStamp(this.bungeobang);
    validationRequireStamp(this.sangsang);
  }

  private void validationRequireStamp(Boolean target) {
    if (!target) {
      throw new RequireStampException("다른 스탬프를 받아야 합니다.");
    }
  }

  private void validationAlreadyStamp(Boolean target) {
    if (target) {
      throw new AlreadyStampException("이미 찍은 스탬프입니다.");
    }
  }

  private void setExhibition(Boolean exhibition) {
    validationAlreadyStamp(this.exhibition);
    this.exhibition = exhibition;
  }

  private void setGround(Boolean ground) {
    validationAlreadyStamp(this.ground);
    this.ground = ground;
  }

  private void setFleamarket(Boolean fleamarket) {
    validationAlreadyStamp(this.fleamarket);
    this.fleamarket = fleamarket;
  }

  private void setBungeobang(Boolean bungeobang) {
    validationAlreadyStamp(this.bungeobang);
    this.bungeobang = bungeobang;
  }

  private void setSangsang(Boolean sangsang) {
    validationAlreadyStamp(this.sangsang);
    this.sangsang = sangsang;
  }
}
