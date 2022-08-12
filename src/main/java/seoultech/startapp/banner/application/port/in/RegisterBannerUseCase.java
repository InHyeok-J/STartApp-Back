package seoultech.startapp.banner.application.port.in;

import seoultech.startapp.banner.application.port.in.command.RegisterBannerCommand;

public interface RegisterBannerUseCase {

  void register(RegisterBannerCommand command);
}
