package seoultech.startapp.banner.application;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.banner.application.port.in.RegisterBannerUseCase;
import seoultech.startapp.banner.application.port.in.command.RegisterBannerCommand;
import seoultech.startapp.banner.application.port.out.SaveBannerPort;
import seoultech.startapp.banner.domain.Banner;

@Service
@RequiredArgsConstructor
public class RegisterBannerService implements RegisterBannerUseCase {

  private final SaveBannerPort saveBannerPort;

  @CacheEvict(value = "bannerCache",allEntries=true)
  @Transactional
  @Override
  public void register(RegisterBannerCommand command) {
    Banner banner = command.toDomainBanner();
    saveBannerPort.save(banner);
  }
}
