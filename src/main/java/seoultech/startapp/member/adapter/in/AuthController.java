package seoultech.startapp.member.adapter.in;

import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seoultech.startapp.global.common.HeaderTokenExtractor;
import seoultech.startapp.global.config.web.AuthMember;
import seoultech.startapp.global.config.web.LoginMember;
import seoultech.startapp.global.response.JsonResponse;
import seoultech.startapp.member.adapter.in.dto.FindPasswordSmsCheckRequest;
import seoultech.startapp.member.adapter.in.dto.FindPasswordSmsPushRequest;
import seoultech.startapp.member.adapter.in.dto.LoginRequest;
import seoultech.startapp.member.adapter.in.dto.SmsCheckRequest;
import seoultech.startapp.member.adapter.in.dto.SmsPushRequest;
import seoultech.startapp.member.application.AccessToken;
import seoultech.startapp.member.application.port.in.SmsAuthUseCase;
import seoultech.startapp.member.application.port.in.command.FindPasswordPushCommand;
import seoultech.startapp.member.application.port.in.command.LoginCommand;
import seoultech.startapp.member.application.port.in.LoginUseCase;
import seoultech.startapp.member.application.AllToken;
import seoultech.startapp.member.application.port.in.command.LogoutCommand;
import seoultech.startapp.member.application.port.in.LogoutUseCase;
import seoultech.startapp.member.application.port.in.command.RefreshCommand;
import seoultech.startapp.member.application.port.in.RefreshUseCase;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

  private final LoginUseCase loginUseCase;
  private final RefreshUseCase refreshUseCase;
  private final LogoutUseCase logoutUseCase;
  private final HeaderTokenExtractor headerTokenExtractor;
  private final SmsAuthUseCase smsAuthUseCase;
  @PostMapping("/sms")
  public ResponseEntity<?> smsPush(@RequestBody SmsPushRequest request){
    smsAuthUseCase.signUpPrePush(request.toCommand());
    return JsonResponse.ok(HttpStatus.OK, "발송 성공");
  }

  @PostMapping("/sms/check")
  public ResponseEntity<?> smsCheck(@RequestBody SmsCheckRequest request){
    smsAuthUseCase.check(request.toCommand());
    return JsonResponse.ok(HttpStatus.OK,"확인 성공");
  }

  @PostMapping("/sms/password")
  public ResponseEntity<?> findPasswordSmsPush(@RequestBody FindPasswordSmsPushRequest request){
    smsAuthUseCase.findPasswordPush(request.toCommand());
    return JsonResponse.ok(HttpStatus.OK, "발송 성공");
  }

  @PostMapping("/sms/password/check")
  public ResponseEntity<?> findPasswordSmsCheck(@RequestBody FindPasswordSmsCheckRequest request){
    smsAuthUseCase.findPasswordCheck(request.toCommand());
    return JsonResponse.ok(HttpStatus.OK, "확인 성공");
  }


  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    LoginCommand command = new LoginCommand(request.getStudentNo(), request.getPassword());
    AllToken allToken = loginUseCase.login(command);
    return JsonResponse.okWithData(HttpStatus.OK, "로그인 성공", allToken);
  }

  @GetMapping(" ")
  public ResponseEntity<?> authCheck(@LoginMember AuthMember authUser) {

    return JsonResponse.okWithData(HttpStatus.OK, "토큰 확인", authUser);
  }

  @GetMapping("/refresh")
  public ResponseEntity<?> refresh(HttpServletRequest request) {
    RefreshCommand command = new RefreshCommand(headerTokenExtractor.extractAccessToken(request),
        headerTokenExtractor.extractRefreshToken(request));
    AccessToken accessToken = refreshUseCase.refresh(command);
    return JsonResponse.okWithData(HttpStatus.OK, "재발급 성공", accessToken);
  }

  @GetMapping("/logout")
  public ResponseEntity<?> logout(@LoginMember AuthMember authUser, HttpServletRequest request) {
    String toke = headerTokenExtractor.extractRefreshToken(request);
    LogoutCommand command = new LogoutCommand(authUser.getMemberId(), toke);

    logoutUseCase.logout(command);

    return JsonResponse.ok(HttpStatus.OK, "로그아웃 성공");
  }
}
