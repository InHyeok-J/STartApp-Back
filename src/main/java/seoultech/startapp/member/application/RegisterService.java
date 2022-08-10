package seoultech.startapp.member.application;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.member.application.port.in.RegisterUseCase;
import seoultech.startapp.member.application.port.in.command.RegisterCommand;
import seoultech.startapp.member.application.port.out.LoadMemberPort;
import seoultech.startapp.member.application.port.out.SaveMemberPort;
import seoultech.startapp.member.domain.Member;
import seoultech.startapp.member.exception.DuplicateStudentNoException;

@Service
@RequiredArgsConstructor
public class RegisterService implements RegisterUseCase {

  private final LoginService loginService;
  private final LoadMemberPort loadMemberPort;
  private final SaveMemberPort saveMemberPort;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  @Override
  public AllToken register(RegisterCommand command) {
    if(loadMemberPort.existByStudentNo(command.getStudentNo())){
      throw new DuplicateStudentNoException("이미 가입된 학번입니다.");
  }
  Member preRegisterMember = MemberFactory.preRegisterMember(command);
    preRegisterMember.changePassword(passwordEncoder.encode(command.getAppPassword()));

  /*
   *  TODO: 자치회비 납부 여부 체크 해야 함.
   */
    preRegisterMember.changeMemberShip(false);

    Member registeredMember = saveMemberPort.save(preRegisterMember);

    return loginService.generateToken(registeredMember);
  }
}
