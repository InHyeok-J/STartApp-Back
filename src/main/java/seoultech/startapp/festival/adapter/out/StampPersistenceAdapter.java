package seoultech.startapp.festival.adapter.out;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import seoultech.startapp.festival.application.port.out.LoadStampPort;
import seoultech.startapp.festival.application.port.out.SaveStampPort;
import seoultech.startapp.festival.domain.Stamp;

@Component
@RequiredArgsConstructor
public class StampPersistenceAdapter implements LoadStampPort, SaveStampPort {

  private final JpaStampRepository jpaStampRepository;
  private final StampMapper stampMapper;
  @Override
  public Stamp loadByMemberId(Long memberId) {
    return jpaStampRepository.findByMemberId(memberId).map(stampMapper::toDomainStamp)
        .orElse(null);
  }

  @Override
  public Stamp save(Stamp stamp) {
    JpaStamp jpaStamp = stampMapper.toJapStamp(stamp);
    JpaStamp savedStamp = jpaStampRepository.save(jpaStamp);
    return stampMapper.toDomainStamp(savedStamp);
  }
}
