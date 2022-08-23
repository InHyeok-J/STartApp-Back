package seoultech.startapp.festival.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.festival.application.port.in.GetBoothUseCase;
import seoultech.startapp.festival.application.port.out.LoadBoothPort;
import seoultech.startapp.festival.application.port.out.LoadLineUpPort;
import seoultech.startapp.festival.domain.Booth;
import seoultech.startapp.festival.domain.LineUp;

@Service
@RequiredArgsConstructor
public class GetBoothService implements GetBoothUseCase {

  private final LoadBoothPort loadBoothPort;
  private final LoadLineUpPort loadLineUpPort;

  @Transactional(readOnly = true)
  @Override
  public BoothLineUpGetResponse getAll() {
    List<Booth> booths = loadBoothPort.loadAll();
    List<LineUp> lineUps = loadLineUpPort.loadListOrderByTime();
    return BoothLineUpGetResponse.builder()
        .boothList(booths.stream().map(BoothResponse::toDto).toList())
        .lineUpList(lineUps.stream().map(LineUpResponse::toDto).toList())
        .build();
  }

  @Transactional(readOnly = true)
  @Override
  public BoothPagingResponse getBoothListPaging(int page, int count) {
    Page<Booth> booths = loadBoothPort.loadByPaging(PageRequest.of(page, count));
    return BoothPagingResponse.toDto(booths.getContent(), booths.getTotalPages());
  }
}
