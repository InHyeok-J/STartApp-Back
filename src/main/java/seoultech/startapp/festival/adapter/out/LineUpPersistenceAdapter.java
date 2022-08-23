package seoultech.startapp.festival.adapter.out;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import seoultech.startapp.festival.application.port.out.DeleteLineUpPort;
import seoultech.startapp.festival.application.port.out.LoadLineUpPort;
import seoultech.startapp.festival.application.port.out.SaveLineUpPort;
import seoultech.startapp.festival.domain.LineUp;

@Component
@RequiredArgsConstructor
public class LineUpPersistenceAdapter implements LoadLineUpPort, SaveLineUpPort , DeleteLineUpPort {

  private final LineUpMapper lineUpMapper;
  private final JpaLineUpRepository jpaLineUpRepository;

  @Override
  public void deleteLineUp(LineUp lineUp) {
    jpaLineUpRepository.delete(lineUpMapper.toJpaEntity(lineUp));
  }

  @Override
  public LineUp loadById(Long lineUpId) {
    JpaLineUp jpaLineUp = jpaLineUpRepository.findById(lineUpId)
        .orElseThrow(() -> new NotFoundLineUpException("해당 라인없이 없습니다."));
    return lineUpMapper.toDomainEntity(jpaLineUp);
  }

  @Override
  public List<LineUp> loadListOrderByTime() {
    List<JpaLineUp> jpaLineUpList = jpaLineUpRepository.findAllByOrderByLineUpTimeAsc();
    return jpaLineUpList.stream().map(lineUpMapper::toDomainEntity).toList();
  }

  @Override
  public void save(LineUp lineUp) {
    jpaLineUpRepository.save(lineUpMapper.toJpaEntity(lineUp));
  }
}
