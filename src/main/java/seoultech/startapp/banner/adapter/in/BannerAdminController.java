package seoultech.startapp.banner.adapter.in;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import seoultech.startapp.banner.application.BannerPagingResponse;
import seoultech.startapp.banner.application.port.in.BannerGetUseCase;
import seoultech.startapp.banner.application.port.in.DeleteBannerUseCase;
import seoultech.startapp.banner.application.port.in.RegisterBannerUseCase;
import seoultech.startapp.banner.application.port.in.UpdatePriorityUserCase;
import seoultech.startapp.global.response.JsonResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/banner")
public class BannerAdminController {

  private final RegisterBannerUseCase registerBannerUseCase;
  private final BannerGetUseCase bannerGetUseCase;
  private final UpdatePriorityUserCase updatePriorityUserCase;
  private final DeleteBannerUseCase deleteBannerUseCase;

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

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteBanner(@PathVariable("id") Long id){
    deleteBannerUseCase.deleteBanner(id);
    return JsonResponse.ok(HttpStatus.OK,"삭제 성공");
  }

  @PatchMapping("/{id}")
  public ResponseEntity<?> updatePriority(@PathVariable("id") Long id, @RequestBody UpdatePriorityRequest dto){
    updatePriorityUserCase.updatePriority(dto.toUpdatePriority(id));
    return JsonResponse.ok(HttpStatus.OK,"업데이트 성공");
  }
}
