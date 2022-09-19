package seoultech.startapp.global.util;

import javax.servlet.http.HttpServletRequest;

public class IpLogUtil {

  public static String getIp(HttpServletRequest request){
    String ip = request.getHeader("X-Forwarded-For");
    if(ip == null){
      ip = request.getRemoteAddr();
    }
    return ip;
  }
}
