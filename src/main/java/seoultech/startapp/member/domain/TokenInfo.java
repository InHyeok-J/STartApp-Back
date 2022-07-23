package seoultech.startapp.member.domain;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

@Getter
public class TokenInfo {

  private Map<String, Object> payload;

  public static TokenInfo accessTokenInfo(Long memberId, MemberRole role) {
    Map<String, Object> accessTokenPayload = new HashMap<>();
    accessTokenPayload.put("memberId", memberId);
    accessTokenPayload.put("role", role);
    return new TokenInfo(accessTokenPayload);
  }

  private TokenInfo(Map<String, Object> payload) {
    this.payload = payload;
  }
}
