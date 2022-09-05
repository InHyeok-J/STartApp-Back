package seoultech.startapp.banner.adapter.in;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seoultech.startapp.banner.application.BannerResponse;
import seoultech.startapp.banner.application.port.in.BannerGetUseCase;
import seoultech.startapp.global.response.JsonResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/banner")
public class BannerController {

  private final BannerGetUseCase bannerGetUseCase;

  @GetMapping("")
  public ResponseEntity<?> getbanner(){
    List<BannerResponse> result = bannerGetUseCase.getAllNotDeleted();
    return JsonResponse.okWithData(HttpStatus.OK, "조회 성공", result);
  }
}
