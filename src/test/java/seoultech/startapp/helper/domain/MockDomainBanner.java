package seoultech.startapp.helper.domain;

import seoultech.startapp.banner.domain.Banner;

public class MockDomainBanner {

  public static Banner generalMockBanner(){
    return Banner.builder()
        .bannerId(1L)
        .imageUrl("imageUrl")
        .title("제목")
        .priority(1)
        .isDeleted(false)
        .build();
  }
}
