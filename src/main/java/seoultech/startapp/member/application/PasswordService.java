package seoultech.startapp.member.application;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.member.application.port.in.PasswordUseCase;
import seoultech.startapp.member.application.port.in.command.MemberPasswordChangeCommand;
import seoultech.startapp.member.application.port.in.command.NotLoginPasswordChangeCommand;
import seoultech.startapp.member.application.port.out.LoadMemberPort;
import seoultech.startapp.member.application.port.out.RedisCachePort;
import seoultech.startapp.member.application.port.out.SaveMemberPort;
import seoultech.startapp.member.domain.Member;
import seoultech.startapp.member.exception.NotMatchPasswordException;
import seoultech.startapp.member.exception.NotMatchPhoneAuthException;

@Service
@RequiredArgsConstructor
public class PasswordService implements PasswordUseCase {

  private final LoadMemberPort loadMemberPort;
  private final SaveMemberPort saveMemberPort;
  private final RedisCachePort redisCachePort;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  @Override
  public void notLoginPasswordChange(NotLoginPasswordChangeCommand command) {
    String saveAuth = redisCachePort.findByKey("PASSWORD-" + command.getStudentNo());

    if (saveAuth == null) {
      throw new NotMatchPhoneAuthException("휴대폰 인증 정보가 일치하지 않습니다.");
    }
    Member member = loadMemberPort.loadByStudentNo(command.getStudentNo());

    member.changePassword(passwordEncoder.encode(command.getPassword()));
    saveMemberPort.save(member);
    redisCachePort.deleteByKey("PASSWORD-" + command.getStudentNo());
  }

  @Transactional
  @Override
  public void memberPasswordChange(MemberPasswordChangeCommand command) {
    Member member = loadMemberPort.loadByMemberId(command.getMemberId());

    if (!passwordEncoder.matches(command.getCurrentPassword(), member.getPassword())) {
      throw new NotMatchPasswordException("패스워드가 일치하지 않습니다");
    }

    member.changePassword(passwordEncoder.encode(command.getNewPassword()));
    saveMemberPort.save(member);
    redisCachePort.deleteByKey("MEMBER-" + member.getMemberId().toString());
  }
}
