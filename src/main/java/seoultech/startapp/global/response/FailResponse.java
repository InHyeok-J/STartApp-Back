package seoultech.startapp.global.response;

import lombok.Getter;

@Getter
public class FailResponse {

  private final int status;
  private final String message;
  private final String errorCode;

  public FailResponse(int status, String message, String errorCode) {
    this.status = status;
    this.message = message;
    this.errorCode = errorCode;
  }
}
