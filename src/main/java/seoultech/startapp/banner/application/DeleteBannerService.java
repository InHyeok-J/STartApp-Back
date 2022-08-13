package seoultech.startapp.banner.application;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.banner.application.port.in.DeleteBannerUseCase;
import seoultech.startapp.banner.application.port.out.LoadBannerPort;
import seoultech.startapp.banner.application.port.out.SaveBannerPort;
import seoultech.startapp.banner.domain.Banner;

@Service
@RequiredArgsConstructor
public class DeleteBannerService implements DeleteBannerUseCase {

  private final LoadBannerPort loadBannerPort;
  private final SaveBannerPort saveBannerPort;

  @CacheEvict(value = "bannerCache",allEntries=true)
  @Transactional
  @Override
  public void deleteBanner(Long bannerId) {
    Banner banner = loadBannerPort.loadById(bannerId);
    banner.delete();
    saveBannerPort.save(banner);
  }
}
