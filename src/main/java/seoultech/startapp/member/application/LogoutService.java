package seoultech.startapp.member.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import seoultech.startapp.member.application.port.in.command.LogoutCommand;
import seoultech.startapp.member.application.port.in.LogoutUseCase;
import seoultech.startapp.member.application.port.out.RedisCachePort;
import seoultech.startapp.member.exception.NotLoginMemberException;
import seoultech.startapp.member.exception.NotMatchLoginInfoException;

@Component
@RequiredArgsConstructor
public class LogoutService implements LogoutUseCase {

  private final RedisCachePort redisCachePort;

  @Override
  public void logout(LogoutCommand command) {

    String saveRefreshToken = redisCachePort.findByKey("MEMBER-"+command.getMemberId().toString());

    if(saveRefreshToken == null){
      throw new NotLoginMemberException("로그인이 안된 멤버입니다");
    }

    if(!saveRefreshToken.equals(command.getRefreshToken())){
      throw new NotMatchLoginInfoException("잘못된 인증 정보입니다.");
    }

    redisCachePort.deleteByKey("MEMBER-"+command.getMemberId().toString());
  }
}
