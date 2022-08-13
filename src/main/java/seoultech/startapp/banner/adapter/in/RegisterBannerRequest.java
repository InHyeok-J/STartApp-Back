package seoultech.startapp.banner.adapter.in;

import seoultech.startapp.banner.application.port.in.command.RegisterBannerCommand;

public record RegisterBannerRequest(String title, String imageUrl, int priority) {

  RegisterBannerCommand toRegisterBannerCommand(){
    return RegisterBannerCommand.builder()
        .title(title)
        .imageUrl(imageUrl)
        .priority(priority)
        .build();
  }
}
