package seoultech.startapp.banner.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import seoultech.startapp.banner.application.port.out.LoadBannerPort;
import seoultech.startapp.banner.domain.Banner;
import seoultech.startapp.helper.domain.MockDomainBanner;

@ExtendWith(MockitoExtension.class)
class BannerGetServiceTest {

  @Mock
  LoadBannerPort loadBannerPort;

  @InjectMocks
  BannerGetService bannerGetService;

  List<Banner> bannerList = new ArrayList<>();
  final int bannerCount = 5;
  @BeforeEach
  void setUp() {
    for (int i = 0; i < bannerCount; i++) {
      bannerList.add(MockDomainBanner.generalMockBanner());
    }
  }

  @Test
  @DisplayName("클라이언트 배너 조회 성공")
  public void bannerSuccess() throws Exception {
    given(loadBannerPort.loadAllByNotDeleted()).willReturn(bannerList);

    List<BannerResponse> result = bannerGetService.getAllNotDeleted();

    assertEquals(result.size(), bannerCount);
  }

  @Test
  @DisplayName("어드민 배너 리스트 조회")
  public void adminBannerSuccess() throws Exception {
    given(loadBannerPort.loadAllByPaging(any())).willReturn(new PageImpl<>(bannerList));

    BannerPagingResponse pagingResult = bannerGetService.getAll(0, 5);

    assertEquals(pagingResult.getTotalPage(), 1);
    assertEquals(pagingResult.getBannerList().size(),bannerCount);
  }
}