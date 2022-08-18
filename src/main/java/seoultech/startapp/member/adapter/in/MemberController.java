package seoultech.startapp.member.adapter.in;

import com.slack.api.app_backend.interactive_components.payload.BlockActionPayload;
import com.slack.api.util.json.GsonFactory;
import javax.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

@RestController
@RequestMapping("/api/member")
@Validated
@RequiredArgsConstructor
public class MemberController {

  private final RegisterUseCase registerUseCase;
  private final MemberGetUserCase memberGetUserCase;

  @PostMapping("")
  public ResponseEntity<?> register(@ModelAttribute RegisterMemberRequest request){
    registerUseCase.register(request.toCommand());
    return JsonResponse.ok(HttpStatus.CREATED,"회원가입 성공");
  }


  @PostMapping("/slack")
  public String test(@RequestParam(required = false) String payload){
    BlockActionPayload blockActionPayload = GsonFactory.createSnakeCase()
        .fromJson(payload, BlockActionPayload.class);
    registerUseCase.studentCardSlackHook(blockActionPayload);
    return "success";
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
