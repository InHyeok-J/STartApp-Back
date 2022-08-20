package seoultech.startapp.festival.application.port.in;

import seoultech.startapp.festival.application.port.in.command.RegisterBoothCommand;

public interface RegisterBoothUseCase {

  void registerBooth(RegisterBoothCommand command);
}
