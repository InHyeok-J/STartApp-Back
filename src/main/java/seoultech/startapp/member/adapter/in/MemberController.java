package seoultech.startapp.member.adapter.in;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seoultech.startapp.global.response.JsonResponse;
import seoultech.startapp.member.application.AllToken;
import seoultech.startapp.member.application.port.in.RegisterUseCase;
import seoultech.startapp.member.application.port.in.command.RegisterCommand;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

  private final RegisterUseCase registerUseCase;

  @PostMapping("")
  public ResponseEntity<?> register(@RequestBody RegisterMemberRequest request){
    AllToken token = registerUseCase.register(new RegisterCommand(request));
    return JsonResponse.okWithData(HttpStatus.CREATED,"회원가입 성공",token);
  }
}
