package seoultech.startapp.member.adapter.in;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import seoultech.startapp.global.response.JsonResponse;
import seoultech.startapp.member.adapter.in.dto.AddMemberShipRequest;
import seoultech.startapp.member.adapter.in.dto.AdminStudentCardAuthRequest;
import seoultech.startapp.member.adapter.in.dto.CheckMemberShipResponse;
import seoultech.startapp.member.adapter.in.dto.UpdateMemberRequest;
import seoultech.startapp.member.adapter.out.NotFoundJpaMemberException;
import seoultech.startapp.member.application.MemberPagingResponse;
import seoultech.startapp.member.application.MemberResponse;
import seoultech.startapp.member.application.port.in.MemberGetUserCase;
import seoultech.startapp.member.application.port.in.MemberShipUseCase;
import seoultech.startapp.member.application.port.in.StudentCardAuthUseCase;
import seoultech.startapp.member.application.port.in.UpdateUseCase;
import seoultech.startapp.member.application.port.in.command.UpdateMemberCommand;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/admin/member")
public class MemberAdminController {

  private final MemberGetUserCase memberGetUserCase;
  private final UpdateUseCase updateUseCase;
  private final StudentCardAuthUseCase studentCardAuthUseCase;
  private final MemberShipUseCase memberShipUseCase;

  @GetMapping("/list")
  public ResponseEntity<?> getMemberList(@RequestParam("page") @NotNull int page,
      @RequestParam("count") @NotNull int count) {
    MemberPagingResponse memberList = memberGetUserCase.getMemberList(page, count);
    return JsonResponse.okWithData(HttpStatus.OK, "회원 조회 성공", memberList);
  }

  @GetMapping("/search")
  public ResponseEntity<?> getMemberByStudentNo(@RequestParam("studentNo") String studentNo) {
    try {
      MemberResponse result = memberGetUserCase.getMemberByStudentNo(studentNo);
      return JsonResponse.okWithData(HttpStatus.OK, "회원 조회 성공", result);

    } catch (NotFoundJpaMemberException e) {
      return JsonResponse.ok(HttpStatus.OK, "해당하는 회원이 없습니다.");
    }
  }

  @GetMapping("/auth/list")
  public ResponseEntity<?> getMemberListPreAuthCard(@RequestParam("page") @NotNull int page,
      @NotNull @RequestParam("count") int count) {
    MemberPagingResponse memberList = memberGetUserCase.getMemberListPreCardAuth(page,
        count);
    return JsonResponse.okWithData(HttpStatus.OK, "학생증 인증 요청 리스트 조회", memberList);
  }

  @GetMapping("/auth/{id}")
  public ResponseEntity<?> getPreAuthCardDetail(@PathVariable("id") Long id) {
    MemberResponse result = memberGetUserCase.getMemberOne(id);
    return JsonResponse.okWithData(HttpStatus.OK, "학생증 인증 상세 보기", result);
  }

  @PatchMapping("/auth/{id}")
  public ResponseEntity<?> checkCardAuth(@PathVariable("id") Long id, @RequestBody
  AdminStudentCardAuthRequest request) {
    studentCardAuthUseCase.cardAuth(request.toCommand(id));
    return JsonResponse.ok(HttpStatus.OK, "학생증 인증 확인 성공");
  }

  @PostMapping("/membership")
  public ResponseEntity<?> addMemberShip(@RequestBody AddMemberShipRequest request) {
    memberShipUseCase.addMemberShip(request.studentNo());
    return JsonResponse.ok(HttpStatus.CREATED, "자치회비 납부 명단 추가 성공");
  }

  @GetMapping("/membership")
  public ResponseEntity<?> checkExistMemberShip(
      @RequestParam("studentNo") @NotBlank String studentNo) {
    boolean result = memberShipUseCase.existCheckMemberShip(studentNo);
    return JsonResponse.okWithData(HttpStatus.OK, "자치회비 명단 확인 조회 성공",
        new CheckMemberShipResponse(result));
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getMemberOne(@PathVariable("id") Long id) {
    MemberResponse result = memberGetUserCase.getMemberOne(id);
    return JsonResponse.okWithData(HttpStatus.OK, "회원 상세 조회 성공", result);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<?> updateMember(@PathVariable("id") Long id,
      @RequestBody UpdateMemberRequest request) {
    UpdateMemberCommand updateMemberCommand = request.toUpdateCommand(id);
    updateUseCase.update(updateMemberCommand);
    return JsonResponse.ok(HttpStatus.OK, "업데이트 성공");
  }

}
