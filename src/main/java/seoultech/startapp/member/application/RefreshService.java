package seoultech.startapp.member.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import seoultech.startapp.global.exception.AuthenticationFailException;
import seoultech.startapp.member.application.port.in.RefreshCommand;
import seoultech.startapp.member.application.port.in.RefreshUseCase;
import seoultech.startapp.member.application.port.out.LoadMemberPort;
import seoultech.startapp.member.application.port.out.RedisCachePort;
import seoultech.startapp.member.domain.Member;
import seoultech.startapp.member.exception.NotLoginMemberException;

@Component
@RequiredArgsConstructor
public class RefreshService implements RefreshUseCase {

  private final LoadMemberPort loadMemberPort;
  private final JwtResolver jwtResolver;
  private final RedisCachePort redisCachePort;
  private final JwtProvider jwtProvider;

  @Override
  public AccessToken refresh(RefreshCommand command) {

    if(!jwtResolver.validateRefreshToken(command.getRefreshToken())){
      throw new AuthenticationFailException("잘못된 인증 정보입니다.", HttpStatus.UNAUTHORIZED);
    }

    Long memberId = jwtResolver.getMemberIdByJwt(command.getAccessToken());

    String savedRefreshToken = redisCachePort.findByKey(memberId.toString());

    if(savedRefreshToken == null){
      throw new NotLoginMemberException("로그인이 안된 멤버입니다.", HttpStatus.UNAUTHORIZED);
    }

    if(!savedRefreshToken.equals(command.getRefreshToken())){
      throw new AuthenticationFailException("잘못된 인증 정보입니다.",  HttpStatus.UNAUTHORIZED);
    }

    Member member = loadMemberPort.loadByMemberId(memberId);

    String accessToken = jwtProvider.createAccessToken( member.createAccessTokenInfo());
    return new AccessToken(accessToken);
  }

}
