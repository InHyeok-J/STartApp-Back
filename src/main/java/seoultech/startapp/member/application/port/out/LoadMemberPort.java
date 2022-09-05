package seoultech.startapp.member.application.port.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import seoultech.startapp.member.domain.Member;

public interface LoadMemberPort {
  Member loadByMemberId(Long memberId);
  Member loadByMemberIdNullable(Long memberId);
  Member loadByStudentNo(String studentNo);
  boolean existByStudentNo(String studentNo);
  Member loadByStudentNoNullable(String studentNo);

  Page<Member> loadNotPreAutMemberByPaging(PageRequest pageRequest);
  Page<Member> loadPreAuthMemberByPaging(PageRequest pageRequest);

  Boolean existsByPhoneNo(String phoneNo);
}
