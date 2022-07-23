package seoultech.startapp.member.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import seoultech.startapp.global.exception.AuthenticationFailException;
import seoultech.startapp.member.application.port.in.LogoutCommand;
import seoultech.startapp.member.application.port.in.LogoutUseCase;
import seoultech.startapp.member.application.port.out.RedisCachePort;
import seoultech.startapp.member.exception.NotLoginMemberException;

@Component
@RequiredArgsConstructor
public class LogoutService implements LogoutUseCase {

  private final RedisCachePort redisCachePort;

  @Override
  public void logout(LogoutCommand command) {

    String saveRefreshToken = redisCachePort.findByKey(command.getMemberId().toString());

    if(saveRefreshToken == null){
      throw new NotLoginMemberException("로그인이 안된 멤버입니다", HttpStatus.UNAUTHORIZED);
    }

    if(!saveRefreshToken.equals(command.getRefreshToken())){
      throw new AuthenticationFailException("잘못된 인증 정보입니다.",  HttpStatus.UNAUTHORIZED);
    }

    redisCachePort.deleteByKey(command.getMemberId().toString());
  }
}
