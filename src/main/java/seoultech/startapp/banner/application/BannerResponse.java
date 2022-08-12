package seoultech.startapp.banner.application;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import seoultech.startapp.banner.domain.Banner;

@Getter
@Builder
public class BannerResponse implements Serializable {

  private Long bannerId;
  private String title;
  private String imageUrl;
  private int priority;
  private Boolean isDeleted;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public static BannerResponse toDto(Banner banner) {
    return BannerResponse.builder()
        .bannerId(banner.getBannerId())
        .title(banner.getTitle())
        .imageUrl(banner.getImageUrl())
        .priority(banner.getPriority())
        .isDeleted(banner.getIsDeleted())
        .createdAt(banner.getCreatedAt())
        .updatedAt(banner.getUpdatedAt())
        .build();
  }
}
