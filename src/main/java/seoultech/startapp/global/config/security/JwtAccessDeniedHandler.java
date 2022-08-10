package seoultech.startapp.global.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import seoultech.startapp.global.exception.ErrorType;
import seoultech.startapp.global.response.FailResponse;

@Slf4j
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException accessDeniedException) throws IOException, ServletException {
    log.error("권한 없음");

    log.error(accessDeniedException.getLocalizedMessage());
    log.error(accessDeniedException.getMessage());
    StackTraceElement[] stackTrace = accessDeniedException.getStackTrace();

    for (StackTraceElement stackTraceElement : stackTrace) {
      System.out.println(stackTraceElement);

    }


    ObjectMapper mapper = new ObjectMapper();
    ErrorType errorType = ErrorType.AUTHORIZATION_FAIL;
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setCharacterEncoding("UTF-8");
    response.setStatus(errorType.getStatusCode());
    FailResponse failResponse = new FailResponse(errorType.getStatusCode(),"권한 없음", errorType.getErrorType());
    response.getWriter()
        .write(mapper.writeValueAsString(failResponse));
  }
}
