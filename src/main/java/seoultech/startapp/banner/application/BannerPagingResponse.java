package seoultech.startapp.banner.application;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;
import seoultech.startapp.banner.domain.Banner;

@Getter
@Builder
public class BannerPagingResponse {
  private List<BannerResponse> bannerList;
  private int totalPage;

  public static BannerPagingResponse toDto(Page<Banner> bannerPage){
    List<BannerResponse> bannerResponseList = bannerPage.getContent().stream()
        .map(BannerResponse::toDto).toList();

    return BannerPagingResponse.builder()
        .bannerList(bannerResponseList)
        .totalPage(bannerPage.getTotalPages())
        .build();
  }
}
