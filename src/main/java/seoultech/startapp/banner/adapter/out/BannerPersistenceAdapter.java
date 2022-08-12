package seoultech.startapp.banner.adapter.out;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import seoultech.startapp.banner.application.port.out.LoadBannerPort;
import seoultech.startapp.banner.application.port.out.SaveBannerPort;
import seoultech.startapp.banner.domain.Banner;

@Component
@RequiredArgsConstructor
public class BannerPersistenceAdapter implements LoadBannerPort, SaveBannerPort {

  private final JpaBannerRepository jpaBannerRepository;
  private final BannerMapper mapper;

  @Override
  public List<Banner> loadAllByNotDeleted() {
    List<JpaBanner> bannerAll = jpaBannerRepository.findAllByIsDeletedFalseOrderByPriorityAsc();
    return bannerAll.stream().map(mapper::mapToDomainBanner).toList();
  }

  @Override
  public Page<Banner> loadAllByPaging(PageRequest request) {
    return jpaBannerRepository.findAllByOrderByIsDeletedAsc(request).map(mapper::mapToDomainBanner);
  }

  @Override
  public void save(Banner banner) {
    JpaBanner jpaBanner = mapper.mapToJpaBanner(banner);
    jpaBannerRepository.save(jpaBanner);
  }
}
