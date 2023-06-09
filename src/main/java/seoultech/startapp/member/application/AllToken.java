package seoultech.startapp.member.application;

import lombok.Getter;

@Getter
public class AllToken {
  private final String accessToken;
  private final String refreshToken;

  public AllToken(String accessToken, String refreshToken) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
  }
}
