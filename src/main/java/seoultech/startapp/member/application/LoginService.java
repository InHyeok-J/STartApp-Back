package seoultech.startapp.member.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.member.application.port.in.LoginCommand;
import seoultech.startapp.member.application.port.in.LoginUseCase;
import seoultech.startapp.member.application.port.out.LoadMemberPort;
import seoultech.startapp.member.domain.Member;
import seoultech.startapp.member.domain.AllToken;
import seoultech.startapp.member.domain.TokenProvider;
import seoultech.startapp.member.exception.NotMatchPasswordException;

@Service
@RequiredArgsConstructor
public class LoginService implements LoginUseCase {

  private final LoadMemberPort loadMemberPort;
  private final TokenProvider tokenProvider;

  @Transactional
  @Override
  public AllToken login(LoginCommand command) {
    Member member = loadMemberPort.loadByStudentNo(command.getStudentNo());
    if(!member.getPassword().equals(command.getPassword())){
      throw new NotMatchPasswordException("패스워드가 일치하지 않습니다", HttpStatus.BAD_REQUEST);
    }

    return tokenProvider.creatToken(member.getMemberId(),member.getMemberRole());
  }
}
