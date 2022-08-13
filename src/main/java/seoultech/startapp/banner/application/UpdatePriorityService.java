package seoultech.startapp.banner.application;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.banner.application.port.in.UpdatePriorityUserCase;
import seoultech.startapp.banner.application.port.in.command.UpdatePriorityCommand;
import seoultech.startapp.banner.application.port.out.LoadBannerPort;
import seoultech.startapp.banner.application.port.out.SaveBannerPort;
import seoultech.startapp.banner.domain.Banner;

@Service
@RequiredArgsConstructor
public class UpdatePriorityService implements UpdatePriorityUserCase {

  private final LoadBannerPort loadBannerPort;
  private final SaveBannerPort saveBannerPort;

  @CacheEvict(value = "bannerCache",allEntries=true)
  @Transactional
  @Override
  public void updatePriority(UpdatePriorityCommand command) {
    Banner banner = loadBannerPort.loadById(command.getBannerId());
    banner.changePriority(command.getPriority());
    saveBannerPort.save(banner);
  }
}
