package seoultech.startapp.festival.adapter.in;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seoultech.startapp.festival.application.BoothResponse;
import seoultech.startapp.festival.application.port.in.GetBoothUseCase;
import seoultech.startapp.global.response.JsonResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/booth")
public class BoothController {

  private final GetBoothUseCase getBoothUseCase;

  @GetMapping("")
  public ResponseEntity<?> getBootList(){
    List<BoothResponse> result = getBoothUseCase.getAll();
    return JsonResponse.okWithData(HttpStatus.OK,"부스 전체 조회", result);
  }

}
