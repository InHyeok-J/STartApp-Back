package seoultech.startapp.member.domain;

import lombok.Getter;

@Getter
public class AccessToken {

  private String accessToken;

  public AccessToken(String accessToken) {
    this.accessToken = accessToken;
  }
}
