package seoultech.startapp.festival.adapter.in;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seoultech.startapp.festival.application.StampResponse;
import seoultech.startapp.festival.application.port.in.command.StampCommand;
import seoultech.startapp.festival.application.port.in.StampUseCase;
import seoultech.startapp.global.config.web.AuthMember;
import seoultech.startapp.global.config.web.LoginMember;
import seoultech.startapp.global.response.JsonResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stamp")
public class StampController {

  private final StampUseCase stampUseCase;

  @PostMapping("")
  public ResponseEntity<?> stamp(@LoginMember AuthMember member, @RequestBody StampRequest request){
    stampUseCase.stamp(StampCommand.builder()
            .memberId(member.getMemberId())
            .target(request.target())
        .build());
    return JsonResponse.ok(HttpStatus.OK ,"스탬프 찍기 성공");
  }

  @GetMapping("")
  public ResponseEntity<?> getStamp(@LoginMember AuthMember member){
    StampResponse myStamp = stampUseCase.getMyStamp(member.getMemberId());
    return JsonResponse.okWithData(HttpStatus.OK, "스탬프 조회 성공", myStamp);
  }
}
