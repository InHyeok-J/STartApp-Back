package seoultech.startapp.global.common;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import seoultech.startapp.global.exception.InvalidInputException;

@Component
public class HeaderTokenExtractor {

  private static final String AUTHORIZATION_HEADER = "Authorization";
  private static final String REFRESH = "Refresh";
  private static final String HEADER_PREFIX = "Bearer ";

  public String extractAccessToken(HttpServletRequest request) {
    String bearerHeader = request.getHeader(AUTHORIZATION_HEADER);

    if (StringUtils.hasText(bearerHeader) && bearerHeader.startsWith(HEADER_PREFIX)) {
      return bearerHeader.substring(HEADER_PREFIX.length());
    }
    throw new InvalidInputException("잘못된 Header Token 값 전송[Access]",HttpStatus.BAD_REQUEST);
  }

  public String extractRefreshToken(HttpServletRequest request){
    String bearerHeader = request.getHeader(REFRESH);

    if (StringUtils.hasText(bearerHeader) && bearerHeader.startsWith(HEADER_PREFIX)) {
      return bearerHeader.substring(HEADER_PREFIX.length());
    }
    throw new InvalidInputException("잘못된 Header Token 값 전송[Refresh]",HttpStatus.BAD_REQUEST);
  }

}
