package seoultech.startapp.festival.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.festival.application.port.in.command.StampCommand;
import seoultech.startapp.festival.application.port.in.StampUseCase;
import seoultech.startapp.festival.application.port.out.LoadStampPort;
import seoultech.startapp.festival.application.port.out.SaveStampPort;
import seoultech.startapp.festival.domain.Stamp;

@Service
@RequiredArgsConstructor
public class StampService implements StampUseCase {

  private final LoadStampPort loadStampPort;
  private final SaveStampPort saveStampPort;

  @Transactional
  @Override
  public void stamp(StampCommand command) {
    Stamp findStamp = loadStampPort.loadByMemberId(command.getMemberId());

    if(findStamp == null){
      Stamp stamp = Stamp.initStamp(command.getMemberId());
      stamp.addStamp(command.getTarget());
      saveStampPort.save(stamp);
    }else {
     findStamp.addStamp(command.getTarget());
     saveStampPort.save(findStamp);
    }
  }

  @Transactional
  @Override
  public StampResponse getMyStamp(Long memberId) {
    Stamp findStamp = loadStampPort.loadByMemberId(memberId);
    if(findStamp == null){
      Stamp savedStamp = saveStampPort.save(Stamp.initStamp(memberId));
      return StampResponse.toDto(savedStamp);
    }
    return StampResponse.toDto(findStamp);
  }
}
