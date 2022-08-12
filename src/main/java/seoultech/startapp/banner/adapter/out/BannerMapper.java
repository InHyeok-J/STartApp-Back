package seoultech.startapp.banner.adapter.out;

import org.springframework.stereotype.Component;
import seoultech.startapp.banner.domain.Banner;

@Component
class BannerMapper {

  public Banner mapToDomainBanner(JpaBanner jpaBanner) {
    return Banner.builder()
        .bannerId(jpaBanner.getId())
        .imageUrl(jpaBanner.getImageUrl())
        .title(jpaBanner.getTitle())
        .priority(jpaBanner.getPriority())
        .isDeleted(jpaBanner.getIsDeleted())
        .createdAt(jpaBanner.getCreatedAt())
        .updatedAt(jpaBanner.getUpdatedAt())
        .build();
  }

  public JpaBanner mapToJpaBanner(Banner banner) {

    return JpaBanner.builder()
        .id(banner.getBannerId() == null ? null : banner.getBannerId())
        .imageUrl(banner.getImageUrl())
        .title(banner.getTitle())
        .priority(banner.getPriority())
        .isDeleted(banner.getIsDeleted())
        .createdAt(banner.getCreatedAt())
        .updatedAt(banner.getUpdatedAt())
        .build();
  }
}
