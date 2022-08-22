package seoultech.startapp.festival.adapter.out;

import org.springframework.stereotype.Component;
import seoultech.startapp.festival.domain.Stamp;

@Component
public class StampMapper {

  public Stamp toDomainStamp(JpaStamp jpaStamp) {
    return Stamp.builder()
        .stampId(jpaStamp.getId())
        .memberId(jpaStamp.getMemberId())
        .exhibition(jpaStamp.getExhibition())
        .ground(jpaStamp.getGround())
        .fleamarket(jpaStamp.getFleamarket())
        .bungeobang(jpaStamp.getBungeobang())
        .sangsang(jpaStamp.getSangsang())
        .isPrized(jpaStamp.getIsPrized())
        .build();
  }


  public JpaStamp toJapStamp(Stamp stamp) {
    return JpaStamp.builder()
        .id(stamp.getStampId() == null ? null : stamp.getStampId())
        .memberId(stamp.getMemberId())
        .exhibition(stamp.getExhibition())
        .ground(stamp.getGround())
        .fleamarket(stamp.getFleamarket())
        .bungeobang(stamp.getBungeobang())
        .sangsang(stamp.getSangsang())
        .isPrized(stamp.getIsPrized())
        .build();
  }
}
