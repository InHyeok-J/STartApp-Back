package seoultech.startapp.global.common;

import javax.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.Getter;
import seoultech.startapp.global.util.IpLogUtil;

@Getter
public class AccessLogDto {

  private String requestMethod;

  private String requestUrl;

  private String requestIp;

  @Builder
  private AccessLogDto(String requestMethod, String requestUrl, String requestIp) {
    this.requestMethod = requestMethod;
    this.requestUrl = requestUrl;
    this.requestIp = requestIp;
  }

  public static AccessLogDto from(HttpServletRequest request){
    return AccessLogDto.builder()
        .requestMethod(request.getMethod())
        .requestUrl(request.getRequestURI())
        .requestIp(IpLogUtil.getIp(request))
        .build();
  }
}
