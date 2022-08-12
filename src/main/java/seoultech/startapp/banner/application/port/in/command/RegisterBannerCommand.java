package seoultech.startapp.banner.application.port.in.command;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import seoultech.startapp.banner.domain.Banner;
import seoultech.startapp.global.common.SelfValidator;

@Getter
@EqualsAndHashCode(callSuper = false)
public class RegisterBannerCommand extends SelfValidator<RegisterBannerCommand> {

  @NotBlank
  @Size(max = 100)
  private final String title;

  @NotBlank
  private final String imageUrl;

  @NotNull
  @Min(1)
  @Max(10)
  private final int priority;

  @Builder
  public RegisterBannerCommand(String title, String imageUrl, int priority) {
    this.title = title;
    this.imageUrl = imageUrl;
    this.priority = priority;
    this.validateSelf();
  }

  public Banner toDomainBanner() {
    return Banner.builder()
        .bannerId(null)
        .title(title)
        .imageUrl(imageUrl)
        .priority(priority)
        .isDeleted(false)
        .build();
  }

  @Override
  public String toString() {
    return "RegisterBannerCommand{" +
        "title='" + title + '\'' +
        ", imageUrl='" + imageUrl + '\'' +
        ", priority=" + priority +
        '}';
  }
}
