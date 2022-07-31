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
import seoultech.startapp.global.exception.BusinessException;
import seoultech.startapp.global.exception.ErrorType;
import seoultech.startapp.global.response.FailResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtExceptionFilter extends OncePerRequestFilter {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    log.info("["+request.getMethod()+"] "+ request.getRequestURI() );
    try{
      filterChain.doFilter(request,response);
    }catch (BusinessException e ){
      ErrorType errorType = e.getErrorType();
      responseHandle(response, errorType, e.getMessage());
    }catch (Exception e) {
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
