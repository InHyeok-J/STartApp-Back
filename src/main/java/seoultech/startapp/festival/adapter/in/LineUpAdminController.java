package seoultech.startapp.festival.adapter.in;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seoultech.startapp.festival.adapter.in.dto.RegisterLineUpRequest;
import seoultech.startapp.festival.application.LineUpResponse;
import seoultech.startapp.festival.application.port.in.GetLineUpUseCase;
import seoultech.startapp.festival.application.port.in.LineUpUseCse;
import seoultech.startapp.global.response.JsonResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/lineup")
public class LineUpAdminController {

  private final LineUpUseCse lineUpUseCse;
  private final GetLineUpUseCase getLineUpUseCase;

  @PostMapping("")
  public ResponseEntity<?> register(@RequestBody RegisterLineUpRequest request){
    lineUpUseCse.register(request.toCommand());
    return JsonResponse.ok(HttpStatus.CREATED, "라인업 생성 성공");
  }

  @GetMapping("")
  public ResponseEntity<?> getList(){
    List<LineUpResponse> result = getLineUpUseCase.findAll();
    return JsonResponse.okWithData(HttpStatus.OK, "라인업 조회 성공", result);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteLineUp(@PathVariable("id") Long lineUpId){
    lineUpUseCse.delete(lineUpId);
    return JsonResponse.ok(HttpStatus.OK,"라인업 삭제 성공");
  }
}
