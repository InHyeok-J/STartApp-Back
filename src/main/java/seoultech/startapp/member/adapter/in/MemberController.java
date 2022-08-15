package seoultech.startapp.member.adapter.in;

import javax.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import seoultech.startapp.global.config.web.AuthMember;
import seoultech.startapp.global.config.web.LoginMember;
import seoultech.startapp.global.response.JsonResponse;
import seoultech.startapp.member.adapter.in.dto.RegisterMemberRequest;
import seoultech.startapp.member.application.AllToken;
import seoultech.startapp.member.application.MemberResponse;
import seoultech.startapp.member.application.port.in.MemberGetUserCase;
import seoultech.startapp.member.application.port.in.RegisterUseCase;
import seoultech.startapp.member.application.port.in.command.RegisterCommand;

@RestController
@RequestMapping("/api/member")
@Validated
@RequiredArgsConstructor
public class MemberController {

  private final RegisterUseCase registerUseCase;
  private final MemberGetUserCase memberGetUserCase;

  @PostMapping("")
  public ResponseEntity<?> register(@RequestBody RegisterMemberRequest request){
    AllToken token = registerUseCase.register(new RegisterCommand(request));
    return JsonResponse.okWithData(HttpStatus.CREATED,"회원가입 성공",token);
  }


  @GetMapping("/duplicate")
  public ResponseEntity<?> duplicateStudentNo(@RequestParam("studentNo") @NotBlank String studentNo){
    memberGetUserCase.checkDuplicateStudentNo(studentNo);
    return JsonResponse.ok(HttpStatus.OK, "중복 검사 통과");
  }

  @GetMapping("")
  public ResponseEntity<?> getMyInfo(@LoginMember AuthMember member){
    MemberResponse result = memberGetUserCase.getMemberOne(member.getMemberId());
    return JsonResponse.okWithData(HttpStatus.OK , "내 정보 조회 성공", result);
  }
}
