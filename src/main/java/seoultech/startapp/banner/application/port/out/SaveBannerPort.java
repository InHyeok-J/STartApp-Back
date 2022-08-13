package seoultech.startapp.banner.application.port.out;

import seoultech.startapp.banner.domain.Banner;

public interface SaveBannerPort {

  void save(Banner banner);
}
