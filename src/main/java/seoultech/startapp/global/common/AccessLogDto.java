package seoultech.startapp.global.common;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AccessLogDto {

  private String requestMethod;

  private String requestUrl;

  private String requestIp;

  private String requestBody;

  @Builder
  public AccessLogDto(String requestMethod, String requestUrl, String requestIp, String requestBody) {
    this.requestMethod = requestMethod;
    this.requestUrl = requestUrl;
    this.requestIp = requestIp;
    this.requestBody = requestBody;
  }

}
