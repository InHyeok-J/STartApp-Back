package seoultech.startapp.banner.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.banner.application.port.in.BannerGetUseCase;
import seoultech.startapp.banner.application.port.out.LoadBannerPort;
import seoultech.startapp.banner.domain.Banner;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BannerGetService implements BannerGetUseCase {

  private final LoadBannerPort loadBannerPort;

  @Cacheable(value = "bannerCache")
  @Override
  public List<BannerResponse> getAllNotDeleted() {
    List<Banner> banners = loadBannerPort.loadAllByNotDeleted();
    return banners.stream().map(BannerResponse::toDto).toList();
  }

  @Override
  public BannerPagingResponse getAll(int page, int count) {
    Page<Banner> banners = loadBannerPort.loadAllByPaging(PageRequest.of(page, count));
    return BannerPagingResponse.toDto(banners);
  }
}
