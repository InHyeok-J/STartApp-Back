package seoultech.startapp.global.common;

import lombok.Builder;
import lombok.Getter;
@Getter
public class ErrorLogDto {

  private String requestMethod;
  private String requestUrl;
  private String errorType;
  private String errorMessage;

  private int statusCode;
  private String requestBody;

  @Builder
  public ErrorLogDto(String requestMethod, String requestUrl, String errorType,
      String errorMessage, int statusCode, String requestBody) {
    this.requestMethod = requestMethod;
    this.requestUrl = requestUrl;
    this.errorType = errorType;
    this.errorMessage = errorMessage;
    this.statusCode = statusCode;
    this.requestBody = requestBody;
  }

  @Override
  public String toString() {
    return "ErrorLogDto{" +
        "requestMethod='" + requestMethod + '\'' +
        ", requestUrl='" + requestUrl + '\'' +
        ", errorType='" + errorType + '\'' +
        ", errorMessage='" + errorMessage + '\'' +
        ", statusCode=" + statusCode +
        ", requestBody='" + requestBody + '\'' +
        '}';
  }
}
