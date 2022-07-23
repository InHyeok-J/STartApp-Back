package seoultech.startapp.member.application;

import lombok.Getter;

@Getter
public class AccessToken {

  private String accessToken;

  public AccessToken(String accessToken) {
    this.accessToken = accessToken;
  }
}
