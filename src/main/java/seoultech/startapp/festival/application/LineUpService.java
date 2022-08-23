package seoultech.startapp.festival.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seoultech.startapp.festival.application.port.in.LineUpUseCse;
import seoultech.startapp.festival.application.port.in.command.RegisterLineUpCommand;
import seoultech.startapp.festival.application.port.out.DeleteLineUpPort;
import seoultech.startapp.festival.application.port.out.LoadLineUpPort;
import seoultech.startapp.festival.application.port.out.SaveLineUpPort;
import seoultech.startapp.festival.domain.LineUp;

@Service
@RequiredArgsConstructor
public class LineUpService implements LineUpUseCse {

  private final LoadLineUpPort loadLineUpPort;
  private final SaveLineUpPort saveLineUpPort;
  private final DeleteLineUpPort deleteLineUpPort;

  @Override
  public void register(RegisterLineUpCommand command) {
    LineUp lineUp = command.toDomainLineUp();
    saveLineUpPort.save(lineUp);
  }

  @Override
  public void delete(Long lineUpId) {
    LineUp lineUp = loadLineUpPort.loadById(lineUpId);
    deleteLineUpPort.deleteLineUp(lineUp);
  }
}
