package seoultech.startapp.member.application;

import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.global.property.JwtProperty;
import seoultech.startapp.member.application.port.in.command.LoginCommand;
import seoultech.startapp.member.application.port.in.LoginUseCase;
import seoultech.startapp.member.application.port.out.LoadMemberPort;
import seoultech.startapp.member.application.port.out.RedisCachePort;
import seoultech.startapp.member.domain.Member;
import seoultech.startapp.member.exception.NotMatchPasswordException;

@Service
@RequiredArgsConstructor
public class LoginService implements LoginUseCase {

  private final LoadMemberPort loadMemberPort;
  private final RedisCachePort redisCachePort;
  private final JwtProperty jwtProperty;
  private final JwtProvider jwtProvider;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  @Override
  public AllToken login(LoginCommand command) {
    Member member = loadMemberPort.loadByStudentNo(command.getStudentNo());

    if(!passwordEncoder.matches(command.getPassword(), member.getPassword())){
      throw new NotMatchPasswordException("패스워드가 일치하지 않습니다");
    }

    return generateToken(member);
  }

  public AllToken generateToken(Member member){
    String accessToken = jwtProvider.createAccessToken(member.createAccessTokenInfo());
    String refreshToken = jwtProvider.createRefreshToken();

    redisCachePort.setStringWithTTL(member.getMemberId().toString(), refreshToken, jwtProperty.getRefreshExpiredDay(), TimeUnit.DAYS);

    return new AllToken(accessToken, refreshToken);
  }
}
