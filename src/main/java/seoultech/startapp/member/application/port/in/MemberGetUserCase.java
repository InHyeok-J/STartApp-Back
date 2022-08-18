package seoultech.startapp.member.application.port.in;

import seoultech.startapp.member.application.MemberPagingResponse;
import seoultech.startapp.member.application.MemberResponse;

public interface MemberGetUserCase {

  MemberPagingResponse getMemberList(int page, int count);

  MemberResponse getMemberOne(Long memberId);

  MemberResponse getMemberByStudentNo(String studentNo);

  MemberResponse getMemberMyInfo(Long memberId);

  void checkDuplicateStudentNo(String studentNo);
}
