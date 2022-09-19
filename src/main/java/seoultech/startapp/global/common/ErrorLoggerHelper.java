package seoultech.startapp.global.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import seoultech.startapp.global.exception.ErrorType;
import seoultech.startapp.global.util.IpLogUtil;

@Slf4j
@Component
public class ErrorLoggerHelper {

  private final ObjectMapper objectMapper = new ObjectMapper();

  public void log(ContentCachingRequestWrapper request, ErrorType errorType, String mesasge)
      throws IOException {
    ErrorLogDto errorLogDto = toErrorLog(request, errorType, mesasge);
    log.error(objectMapper.writeValueAsString(errorLogDto));
  }

  private ErrorLogDto toErrorLog(ContentCachingRequestWrapper request, ErrorType errorType,
      String message)
      throws IOException {
    return ErrorLogDto.builder()
        .requestMethod(request.getMethod())
        .requestUrl(request.getRequestURI())
        .requestIp(IpLogUtil.getIp(request))
        .errorMessage(message)
        .errorType(errorType.getErrorType())
        .statusCode(errorType.getStatusCode())
        .requestBody(getRequestBody(request))
        .build();
  }

  private String getRequestBody(ContentCachingRequestWrapper request) throws IOException {
    if (isReadableRequestBody(request)) {
      return new String(request.getContentAsByteArray());
    }
    return null;
  }

  private boolean isReadableRequestBody(ContentCachingRequestWrapper request) {
    String method = request.getMethod().toUpperCase();
    String contentType = request.getContentType();
    if(contentType == null){
      return false;
    }
    /*
     *  POST ,PUT, application-json 방식만 허용.
     */
    if (method.startsWith("P") && contentType.equalsIgnoreCase("application/json")) {
      return true;
    }
    return false;
  }
}
