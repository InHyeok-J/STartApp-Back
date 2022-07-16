package seoultech.startapp.member.domain;

import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenProvider {

  private final JwtProvider jwtProvider;

  public AllToken creatToken(Long memberId, MemberRole role ){
    Map<String, Object> payload = new HashMap<>();
    payload.put("memberId", memberId);
    payload.put("role", role);

   String accessToken = jwtProvider.createAccessToken(payload);
   String refreshToken = jwtProvider.createRefreshToken();

   return new AllToken(accessToken, refreshToken);
  }

  public AccessToken createAccessToken(Long memberId, MemberRole role){
    Map<String, Object> payload = new HashMap<>();
    payload.put("memberId", memberId);
    payload.put("role", role);
    return new AccessToken(jwtProvider.createAccessToken(payload));
  }
}
