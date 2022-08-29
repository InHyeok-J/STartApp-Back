package seoultech.startapp.festival.adapter.in;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import seoultech.startapp.festival.adapter.in.dto.RegisterBoothRequest;
import seoultech.startapp.festival.adapter.in.dto.UpdateCongestionBootRequest;
import seoultech.startapp.festival.application.BoothPagingResponse;
import seoultech.startapp.festival.application.port.in.DeleteBoothUseCase;
import seoultech.startapp.festival.application.port.in.GetBoothUseCase;
import seoultech.startapp.festival.application.port.in.RegisterBoothUseCase;
import seoultech.startapp.festival.application.port.in.UpdateCongestionBoothUseCase;
import seoultech.startapp.global.response.JsonResponse;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/admin/booth")
public class BoothAdminController {

  private final RegisterBoothUseCase registerBoothUseCase;
  private final UpdateCongestionBoothUseCase updateCongestionBoothUseCase;
  private final GetBoothUseCase getBoothUseCase;
  private final DeleteBoothUseCase deleteBoothUseCase;

  @PostMapping("")
  public ResponseEntity<?> createBooth(@RequestBody RegisterBoothRequest request) {
    registerBoothUseCase.registerBooth(request.toCommand());
    return JsonResponse.ok(HttpStatus.CREATED, "부스 생성 성공");
  }

  @PatchMapping("/{id}")
  public ResponseEntity<?> updateBooth(
      @PathVariable("id") Long boothId,
      @RequestBody UpdateCongestionBootRequest request) {
    updateCongestionBoothUseCase.update(request.toCommand(boothId));
    return JsonResponse.ok(HttpStatus.OK, "수정 성공");
  }

  @GetMapping("/list")
  public ResponseEntity<?> getBoothList(@RequestParam("page") @NotNull @Min(0) int page,
      @RequestParam("count") @NotNull @Min(1)  int count) {
    BoothPagingResponse result = getBoothUseCase.getBoothListPaging(page, count);
    return JsonResponse.okWithData(HttpStatus.OK, "부스 리스트 조회 성공", result);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteBooth(@PathVariable("id") Long id) {
    deleteBoothUseCase.deleteBooth(id);
    return JsonResponse.ok(HttpStatus.OK, "삭제 성공");
  }
}
