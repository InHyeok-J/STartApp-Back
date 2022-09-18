package seoultech.startapp.global.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import seoultech.startapp.global.common.ErrorLoggerHelper;
import seoultech.startapp.global.exception.BusinessException;
import seoultech.startapp.global.exception.ErrorType;
import seoultech.startapp.global.response.FailResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtExceptionFilter extends OncePerRequestFilter {

  private final ObjectMapper objectMapper = new ObjectMapper();
  private final ErrorLoggerHelper errorLoggerHelper;
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    ContentCachingRequestWrapper wrapper = new ContentCachingRequestWrapper(request);
    try{
      filterChain.doFilter(request,response);
    }catch (BusinessException e ){
      ErrorType errorType = e.getErrorType();
      errorLoggerHelper.log(wrapper,errorType,e.getMessage());
      responseHandle(response, errorType, e.getMessage());
    }catch (Exception e) {
      errorLoggerHelper.log(wrapper,ErrorType.INTERNAL_SERVER_ERROR,"서버 에러");
      responseHandle(response,ErrorType.INTERNAL_SERVER_ERROR, "서버에러");
    }
  }

  private void responseHandle(HttpServletResponse response, ErrorType errorType, String message)
      throws IOException {
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setCharacterEncoding("UTF-8");
    response.setStatus(errorType.getStatusCode());
    FailResponse failResponse = new FailResponse(errorType.getStatusCode(),message, errorType.getErrorType());
    response.getWriter()
        .write(objectMapper.writeValueAsString(failResponse));
  }
}
