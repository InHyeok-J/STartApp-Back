package seoultech.startapp.member.adapter.in;

import javax.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import seoultech.startapp.global.response.JsonResponse;
import seoultech.startapp.member.application.MemberPagingResponse;
import seoultech.startapp.member.application.port.in.MemberGetUserCase;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/member")
public class MemberAdminController {

  private final MemberGetUserCase memberGetUserCase;

  @RolesAllowed("ADMIN")
  @GetMapping("/list")
  public ResponseEntity<?> getMemberList(@RequestParam("page") int page, @RequestParam("count") int count ){
    MemberPagingResponse memberList = memberGetUserCase.getMemberList(page, count);
    return JsonResponse.okWithData(HttpStatus.OK, "회원 조회 성공", memberList);
  }
}
