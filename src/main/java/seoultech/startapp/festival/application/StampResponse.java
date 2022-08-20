package seoultech.startapp.festival.application;

import lombok.Builder;
import lombok.Getter;
import seoultech.startapp.festival.domain.Stamp;

@Getter
public class StampResponse {

  private Long stampId;
  private Long memberId;
  private Boolean exhibition;
  private Boolean ground;
  private Boolean fleamarket;
  private Boolean bungeobang;
  private Boolean sangsang;

  @Builder
  public StampResponse(Long stampId, Long memberId, Boolean exhibition, Boolean ground,
      Boolean fleamarket, Boolean bungeobang, Boolean sangsang) {
    this.stampId = stampId;
    this.memberId = memberId;
    this.exhibition = exhibition;
    this.ground = ground;
    this.fleamarket = fleamarket;
    this.bungeobang = bungeobang;
    this.sangsang = sangsang;
  }

  public static StampResponse toDto(Stamp stamp){
    return StampResponse.builder()
        .stampId(stamp.getStampId())
        .memberId(stamp.getMemberId())
        .exhibition(stamp.getExhibition())
        .ground(stamp.getGround())
        .fleamarket(stamp.getFleamarket())
        .bungeobang(stamp.getBungeobang())
        .sangsang(stamp.getSangsang())
        .build();
  }
}
