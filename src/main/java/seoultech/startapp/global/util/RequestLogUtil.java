package seoultech.startapp.global.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import org.springframework.util.StreamUtils;
import seoultech.startapp.global.common.ReusableRequestWrapper;

public class RequestLogUtil {

  public static String getIp(HttpServletRequest request) {
    String ip = request.getHeader("X-Forwarded-For");
    if (ip == null) {
      ip = request.getRemoteAddr();
    }
    return ip;
  }

  public static String getBody(ReusableRequestWrapper request) throws IOException {
    String method = request.getMethod().toUpperCase();
    String contentType = request.getContentType();
    if (contentType == null) {
      return null;
    }

    if (method.startsWith("P") && contentType.contains("application/json")) {
      ServletInputStream inputStream = request.getInputStream();
      String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
      return messageBody;
    } else {
      return null;
    }
  }
}
