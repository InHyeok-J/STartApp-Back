package seoultech.startapp.festival.adapter.in;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seoultech.startapp.global.response.JsonResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/festival")
public class FestivalController {

  @GetMapping("")
  public ResponseEntity<?> check() {
    return JsonResponse.ok(HttpStatus.OK, "ok");
  }
}
