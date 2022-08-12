package seoultech.startapp.banner.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Banner {

  private Long bannerId;
  private String title;
  private String imageUrl;
  private int priority;
  private Boolean isDeleted;

  @Builder
  public Banner(Long bannerId, String title, String imageUrl, int priority,
      Boolean isDeleted) {
    this.bannerId = bannerId;
    this.title = title;
    this.imageUrl = imageUrl;
    this.priority = priority;
    this.isDeleted = isDeleted;
  }
}
