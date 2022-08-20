package seoultech.startapp.festival.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.festival.application.port.in.UpdateCongestionBoothUseCase;
import seoultech.startapp.festival.application.port.in.command.UpdateCongestionBoothCommand;
import seoultech.startapp.festival.application.port.out.LoadBoothPort;
import seoultech.startapp.festival.application.port.out.SaveBoothPort;
import seoultech.startapp.festival.domain.Booth;

@Service
@RequiredArgsConstructor
public class UpdateBoothService implements UpdateCongestionBoothUseCase {

  private final LoadBoothPort loadBoothPort;
  private final SaveBoothPort saveBoothPort;

  @Transactional
  @Override
  public void update(UpdateCongestionBoothCommand command) {
    Booth booth = loadBoothPort.loadByBoothId(command.getBoothId());
    booth.changeCongestion(command.getCongestion());
    saveBoothPort.save(booth);
  }
}
