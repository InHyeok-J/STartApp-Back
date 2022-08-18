package seoultech.startapp.member.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.member.application.port.in.StudentCardAuthUseCase;
import seoultech.startapp.member.application.port.out.LoadMemberPort;
import seoultech.startapp.member.application.port.out.SaveMemberPort;
import seoultech.startapp.member.domain.Member;
import seoultech.startapp.member.domain.MemberStatus;
import seoultech.startapp.member.exception.AlreadyCardAuthException;

@Service
@RequiredArgsConstructor
public class StudentCardAuthService implements StudentCardAuthUseCase {

  private final LoadMemberPort loadMemberPort;
  private final SaveMemberPort saveMemberPort;

  @Transactional
  @Override
  public void cardAuth(Long memberId) {
    Member member = loadMemberPort.loadByMemberId(memberId);
    if(!member.getMemberStatus().equals(MemberStatus.PRE_CARD_AUTH)){
      throw new AlreadyCardAuthException("이미 학생증 인증이 된 회원입니다.");
    }
    member.cardApprove();
    saveMemberPort.save(member);
  }
}
