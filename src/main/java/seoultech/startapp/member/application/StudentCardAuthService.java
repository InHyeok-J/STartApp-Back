package seoultech.startapp.member.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.member.application.port.in.StudentCardAuthUseCase;
import seoultech.startapp.member.application.port.in.command.StudentCardAuthCommand;
import seoultech.startapp.member.application.port.out.DeleteMemberPort;
import seoultech.startapp.member.application.port.out.LoadMemberPort;
import seoultech.startapp.member.application.port.out.SaveMemberPort;
import seoultech.startapp.member.application.port.out.SmsPushPort;
import seoultech.startapp.member.domain.Member;
import seoultech.startapp.member.domain.MemberStatus;
import seoultech.startapp.member.exception.AlreadyCardAuthException;

@Service
@RequiredArgsConstructor
public class StudentCardAuthService implements StudentCardAuthUseCase {

  private final LoadMemberPort loadMemberPort;
  private final SaveMemberPort saveMemberPort;
  private final SmsPushPort smsPushPort;
  private final DeleteMemberPort deleteMemberPort;

  @Transactional
  @Override
  public void cardAuth(StudentCardAuthCommand command) {
    Member member = loadMemberPort.loadByMemberId(command.getMemberId());
    if (!member.getMemberStatus().equals(MemberStatus.PRE_CARD_AUTH)) {
      throw new AlreadyCardAuthException("이미 학생증 인증이 된 회원입니다.");
    }

    if (command.isAuth()) {
      member.cardApprove();
      saveMemberPort.save(member);
      smsPushPort.pushApprove(member.getProfile().getPhoneNo());
    }else{
      deleteMemberPort.deleteMember(member);
      smsPushPort.pushReject(member.getProfile().getPhoneNo());
    }
  }
}
