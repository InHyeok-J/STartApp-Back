package seoultech.startapp.festival.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.festival.application.port.in.GetLineUpUseCase;
import seoultech.startapp.festival.application.port.out.LoadLineUpPort;
import seoultech.startapp.festival.domain.LineUp;

@Service
@RequiredArgsConstructor
public class GetLineUpService implements GetLineUpUseCase {

  private final LoadLineUpPort loadLineUpPort;

  @Transactional(readOnly = true)
  @Override
  public List<LineUpResponse> findAll() {
    List<LineUp> lineUpList = loadLineUpPort.loadListOrderByTime();
    return lineUpList.stream().map(LineUpResponse::toDto).toList();
  }
}
