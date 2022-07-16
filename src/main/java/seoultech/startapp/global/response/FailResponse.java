package seoultech.startapp.global.response;

import lombok.Getter;

@Getter
public class FailResponse {

  private final int status;
  private final String message;

  public FailResponse(int status, String message) {
    this.status = status;
    this.message = message;
  }
}
