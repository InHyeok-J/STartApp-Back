package seoultech.startapp.festival.adapter.out;

import org.springframework.stereotype.Component;
import seoultech.startapp.festival.domain.LineUp;

@Component
public class LineUpMapper {

  public LineUp toDomainEntity(JpaLineUp jpaLineUp){
    return LineUp.builder()
        .lineUpId(jpaLineUp.getId())
        .lineUpDay(jpaLineUp.getLineUpDay())
        .lineUpTime(jpaLineUp.getLineUpTime())
        .lineUpTitle(jpaLineUp.getTitle())
        .build();
  }

  public JpaLineUp toJpaEntity(LineUp lineUp){
    return JpaLineUp.builder()
        .id(lineUp.getLineUpId() == null ? null : lineUp.getLineUpId())
        .lineUpDay(lineUp.getLineUpDay())
        .lineUpTime(lineUp.getLineUpTime())
        .title(lineUp.getLineUpTitle())
        .build() ;
  }
}
