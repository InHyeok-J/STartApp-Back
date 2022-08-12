package seoultech.startapp.banner.application;

import lombok.Builder;
import lombok.Getter;
import seoultech.startapp.banner.domain.Banner;

@Getter
@Builder
public class BannerResponse {

  private Long bannerId;
  private String title;
  private String imageUrl;
  private int priority;
  private Boolean isDeleted;

  public static  BannerResponse toDto(Banner banner){
    return BannerResponse.builder()
        .bannerId(banner.getBannerId())
        .title(banner.getTitle())
        .imageUrl(banner.getImageUrl())
        .priority(banner.getPriority())
        .isDeleted(banner.getIsDeleted())
        .build();
  }
}
