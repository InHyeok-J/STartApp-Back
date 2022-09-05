package seoultech.startapp.member.adapter.in;

import com.slack.api.app_backend.interactive_components.payload.BlockActionPayload;
import com.slack.api.util.json.GsonFactory;
import javax.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import seoultech.startapp.global.config.web.AuthMember;
import seoultech.startapp.global.config.web.LoginMember;
import seoultech.startapp.global.response.JsonResponse;
import seoultech.startapp.member.adapter.in.dto.NotLoginPasswordChangeRequest;
import seoultech.startapp.member.adapter.in.dto.RegisterMemberRequest;
import seoultech.startapp.member.adapter.in.dto.MemberPasswordChangeRequest;
import seoultech.startapp.member.application.MemberResponse;
import seoultech.startapp.member.application.port.in.LeaveMemberUseCase;
import seoultech.startapp.member.application.port.in.MemberGetUserCase;
import seoultech.startapp.member.application.port.in.PasswordUseCase;
import seoultech.startapp.member.application.port.in.RegisterUseCase;

@RestController
@RequestMapping("/api/v1/member")
@Validated
@RequiredArgsConstructor
@Slf4j
public class MemberController {

  private final RegisterUseCase registerUseCase;
  private final MemberGetUserCase memberGetUserCase;
  private final PasswordUseCase passwordUseCase;
  private final LeaveMemberUseCase leaveMemberUseCase;
  @PostMapping("")
  public ResponseEntity<?> register(@ModelAttribute RegisterMemberRequest request) {
    log.info(request.toString());
    log.info("FILE SIZE : " + request.file().getSize());
    registerUseCase.register(request.toCommand());
    return JsonResponse.ok(HttpStatus.CREATED, "회원가입 성공");
  }

  @PatchMapping("/password")
  public ResponseEntity<?> notLoginPasswordChange(
      @RequestBody NotLoginPasswordChangeRequest request) {
    passwordUseCase.notLoginPasswordChange(request.toCommand());
    return JsonResponse.ok(HttpStatus.OK, "패스워드 변경 성공");
  }

  @PatchMapping("/login/password")
  public ResponseEntity<?> userPasswordChange(@LoginMember AuthMember member, @RequestBody
  MemberPasswordChangeRequest request){
    passwordUseCase.memberPasswordChange(request.toCommand(member.getMemberId()));
    return JsonResponse.ok(HttpStatus.OK, "패스워드 변경 성공");
  }

  @PostMapping("/slack")
  public String test(@RequestParam(required = false) String payload) {
    BlockActionPayload blockActionPayload = GsonFactory.createSnakeCase()
        .fromJson(payload, BlockActionPayload.class);
    registerUseCase.studentCardSlackHook(blockActionPayload);
    return "success";
  }

  @GetMapping("/duplicate")
  public ResponseEntity<?> duplicateStudentNo(
      @RequestParam("studentNo") @NotBlank String studentNo) {
    memberGetUserCase.checkDuplicateStudentNo(studentNo);
    return JsonResponse.ok(HttpStatus.OK, "중복 검사 통과");
  }

  @GetMapping("")
  public ResponseEntity<?> getMyInfo(@LoginMember AuthMember member) {
    MemberResponse result = memberGetUserCase.getMemberMyInfo(member.getMemberId());
    return JsonResponse.okWithData(HttpStatus.OK, "내 정보 조회 성공", result);
  }

  @DeleteMapping("")
  public ResponseEntity<?> leaveMember(@LoginMember AuthMember member){
    leaveMemberUseCase.leave(member.getMemberId());
    return JsonResponse.ok(HttpStatus.OK,"탈퇴 성공");
  }
}
