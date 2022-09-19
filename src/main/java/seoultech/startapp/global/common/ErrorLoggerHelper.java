package seoultech.startapp.global.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import seoultech.startapp.global.exception.ErrorType;
import seoultech.startapp.global.util.RequestLogUtil;

@Slf4j
@Component
public class ErrorLoggerHelper {

  private final ObjectMapper objectMapper = new ObjectMapper();

  public void log(ReusableRequestWrapper request, ErrorType errorType, String mesasge)
      throws IOException {
    ErrorLogDto errorLogDto = toErrorLog(request, errorType, mesasge);
    log.error(objectMapper.writeValueAsString(errorLogDto));
  }

  private ErrorLogDto toErrorLog(ReusableRequestWrapper request, ErrorType errorType,
      String message)
      throws IOException {
    return ErrorLogDto.builder()
        .requestMethod(request.getMethod())
        .requestUrl(request.getRequestURI())
        .requestIp(RequestLogUtil.getIp(request))
        .errorMessage(message)
        .errorType(errorType.getErrorType())
        .statusCode(errorType.getStatusCode())
        .requestBody(RequestLogUtil.getBody(request))
        .build();
  }
}
