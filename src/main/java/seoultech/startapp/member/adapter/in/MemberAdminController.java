package seoultech.startapp.member.adapter.in;

import javax.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import seoultech.startapp.global.response.JsonResponse;
import seoultech.startapp.member.adapter.out.NotFoundJpaMemberException;
import seoultech.startapp.member.application.MemberListResponse;
import seoultech.startapp.member.application.MemberPagingResponse;
import seoultech.startapp.member.application.MemberResponse;
import seoultech.startapp.member.application.port.in.MemberGetUserCase;
import seoultech.startapp.member.application.port.in.UpdateUseCase;
import seoultech.startapp.member.application.port.in.command.UpdateMemberCommand;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/member")
public class MemberAdminController {

  private final MemberGetUserCase memberGetUserCase;
  private final UpdateUseCase updateUseCase;

  @GetMapping("/list")
  public ResponseEntity<?> getMemberList(@RequestParam("page") int page, @RequestParam("count") int count ){
    MemberPagingResponse memberList = memberGetUserCase.getMemberList(page, count);
    return JsonResponse.okWithData(HttpStatus.OK, "회원 조회 성공", memberList);
  }

  @GetMapping("/search")
  public ResponseEntity<?> getMemberByStudentNo(@RequestParam("studentNo") String studentNo){
    try {
      MemberListResponse result = memberGetUserCase.getMemberByStudentNo(studentNo);
      return JsonResponse.okWithData(HttpStatus.OK, "회원 조회 성공", result);

    }catch (NotFoundJpaMemberException e){
      return JsonResponse.ok(HttpStatus.OK, "해당하는 회원이 없습니다.");
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getMemberOne(@PathVariable("id") Long id){
    MemberResponse result = memberGetUserCase.getMemberOne(id);
    return JsonResponse.okWithData(HttpStatus.OK, "회원 상세 조회 성공", result);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<?> updateMember(@PathVariable("id") Long id, @RequestBody UpdateMemberRequest request){
    UpdateMemberCommand updateMemberCommand = request.toUpdateCommand(id);
    updateUseCase.update(updateMemberCommand);
    return JsonResponse.ok(HttpStatus.OK, "업데이트 성공");
  }
}
