package seoultech.startapp.member.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.member.application.port.in.MemberGetUserCase;
import seoultech.startapp.member.application.port.out.LoadMemberPort;
import seoultech.startapp.member.domain.Member;
import seoultech.startapp.member.exception.DuplicateStudentNoException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberGetService implements MemberGetUserCase {

  private final LoadMemberPort loadMemberPort;

  @Override
  public MemberPagingResponse getMemberList(int page, int count) {
    Page<Member> pageMembers = loadMemberPort.loadByPaging(PageRequest.of(page, count));
    return MemberPagingResponse.toDto(pageMembers.getContent(), pageMembers.getTotalPages());
  }

  @Override
  public MemberResponse getMemberOne(Long memberId) {
    Member findMember = loadMemberPort.loadByMemberId(memberId);

    return MemberResponse.toDto(findMember);
  }

  @Override
  public MemberResponse getMemberByStudentNo(String studentNo) {
    Member findMember = loadMemberPort.loadByStudentNo(studentNo);
    return MemberResponse.toSummaryDto(findMember);
  }

  @Override
  public void checkDuplicateStudentNo(String studentNo) {
    if(loadMemberPort.existByStudentNo(studentNo)) {
      throw new DuplicateStudentNoException("이미 있는 학번입니다.");
    }
  }
}
