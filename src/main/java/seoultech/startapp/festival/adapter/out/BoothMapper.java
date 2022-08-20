package seoultech.startapp.festival.adapter.out;

import org.springframework.stereotype.Component;
import seoultech.startapp.festival.domain.Booth;

@Component
public class BoothMapper {

  public Booth toDomainBooth(JpaBooth jpaBooth){
    return Booth.builder()
        .boothId(jpaBooth.getId())
        .name(jpaBooth.getName())
        .congestion(jpaBooth.getCongestion())
        .build();
  }

  public JpaBooth toJpaBooth(Booth booth){
    return JpaBooth.builder()
        .id(booth.getBoothId() == null ? null : booth.getBoothId())
        .name(booth.getName())
        .congestion(booth.getCongestion())
        .build();
  }
}
