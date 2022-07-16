package seoultech.startapp.member.adapter.in;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seoultech.startapp.global.response.JsonResponse;
import seoultech.startapp.member.application.port.in.LoginCommand;
import seoultech.startapp.member.application.port.in.LoginUseCase;
import seoultech.startapp.member.domain.AllToken;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

  private final LoginUseCase loginUseCase;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest request){
    LoginCommand command = new LoginCommand(request.getStudentNo(), request.getPassword());
    AllToken allToken = loginUseCase.login(command);
    return JsonResponse.okWithData(HttpStatus.OK, "로그인 성공", allToken);
  }
}
