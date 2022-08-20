package seoultech.startapp.festival.application.port.in;

import seoultech.startapp.festival.application.port.in.command.UpdateCongestionBoothCommand;

public interface UpdateCongestionBoothUseCase {

  void update(UpdateCongestionBoothCommand command);
}
