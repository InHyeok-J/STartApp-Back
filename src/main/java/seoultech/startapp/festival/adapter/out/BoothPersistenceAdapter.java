package seoultech.startapp.festival.adapter.out;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import seoultech.startapp.festival.application.port.out.DeleteBoothPort;
import seoultech.startapp.festival.application.port.out.LoadBoothPort;
import seoultech.startapp.festival.application.port.out.SaveBoothPort;
import seoultech.startapp.festival.domain.Booth;

@Component
@RequiredArgsConstructor
public class BoothPersistenceAdapter implements LoadBoothPort, SaveBoothPort, DeleteBoothPort {

  private final BoothMapper boothMapper;
  private final JpaBoothRepository jpaBoothRepository;

  @Override
  public void deleteBoothBooth(Booth booth) {
    jpaBoothRepository.delete(boothMapper.toJpaBooth(booth));
  }

  @Override
  public Booth loadByBoothId(Long id) {
    JpaBooth jpaBooth = jpaBoothRepository.findById(id)
        .orElseThrow(() -> new NotFoundJpaBoothException("해당 부스를 찾을 수 없습니다."));
    return boothMapper.toDomainBooth(jpaBooth);
  }

  @Override
  public Page<Booth> loadByPaging(PageRequest pageRequest) {
    return jpaBoothRepository.findAllByOrderByIdDesc(pageRequest)
        .map(boothMapper::toDomainBooth);
  }

  @Override
  public List<Booth> loadAll() {
    List<JpaBooth> jpaBoothList = jpaBoothRepository.findAll();
    return jpaBoothList.stream().map(boothMapper::toDomainBooth).toList();
  }

  @Override
  public void save(Booth booth) {
    jpaBoothRepository.save(boothMapper.toJpaBooth(booth));
  }
}
