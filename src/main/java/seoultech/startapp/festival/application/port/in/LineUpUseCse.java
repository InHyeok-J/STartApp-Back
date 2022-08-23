package seoultech.startapp.festival.application.port.in;

import seoultech.startapp.festival.application.port.in.command.RegisterLineUpCommand;

public interface LineUpUseCse {

  void register(RegisterLineUpCommand command);
  void delete(Long lineUpId);
}
