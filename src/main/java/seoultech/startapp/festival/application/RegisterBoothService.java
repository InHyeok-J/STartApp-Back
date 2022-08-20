package seoultech.startapp.festival.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.festival.application.port.in.RegisterBoothUseCase;
import seoultech.startapp.festival.application.port.in.command.RegisterBoothCommand;
import seoultech.startapp.festival.application.port.out.SaveBoothPort;
import seoultech.startapp.festival.domain.Booth;

@Service
@RequiredArgsConstructor
public class RegisterBoothService implements RegisterBoothUseCase {

  private final SaveBoothPort saveBoothPort;

  @Transactional
  @Override
  public void registerBooth(RegisterBoothCommand command) {
    Booth booth = command.toDomainBooth();
    saveBoothPort.save(booth);
  }
}
