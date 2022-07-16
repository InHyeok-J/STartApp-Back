package seoultech.startapp.member.application.port.out;

import seoultech.startapp.member.domain.Member;

public interface LoadMemberPort {
  Member loadByMemberId(Long memberId);
  Member loadByStudentNo(String studentNo);
}
