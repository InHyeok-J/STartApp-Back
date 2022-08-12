package seoultech.startapp.banner.adapter.in;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import seoultech.startapp.banner.application.BannerPagingResponse;
import seoultech.startapp.banner.application.port.in.BannerGetUseCase;
import seoultech.startapp.banner.application.port.in.RegisterBannerUseCase;
import seoultech.startapp.global.response.JsonResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/banner")
public class BannerAdminController {

  private final RegisterBannerUseCase registerBannerUseCase;
  private final BannerGetUseCase bannerGetUseCase;

  @PostMapping("")
  public ResponseEntity<?> register(@RequestBody RegisterBannerRequest dto){
    registerBannerUseCase.register(dto.toRegisterBannerCommand());
    return JsonResponse.ok(HttpStatus.CREATED, "배너 생성 성공");
  }

  @GetMapping("/list")
  public ResponseEntity<?> getBannerList(@RequestParam("page") int page, @RequestParam("count") int count){
    BannerPagingResponse result = bannerGetUseCase.getAll(page, count);
    return JsonResponse.okWithData(HttpStatus.OK, "배너 조회 성공", result);
  }
}
