package seoultech.startapp.global.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import seoultech.startapp.global.exception.AuthenticationFailException;
import seoultech.startapp.global.exception.InvalidInputException;
import seoultech.startapp.global.response.FailResponse;

@Component
@RequiredArgsConstructor
public class JwtExceptionFilter extends OncePerRequestFilter {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    try{
      filterChain.doFilter(request,response);
    }catch (InvalidInputException | AuthenticationFailException e ){
      responseHandle(response, e.getStatus(), e.getMessage());
    }catch (Exception e) {
      responseHandle(response,HttpStatus.INTERNAL_SERVER_ERROR , "서버에러");
    }
  }


  private void responseHandle(HttpServletResponse response, HttpStatus httpStatus, String message)
      throws IOException {
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setCharacterEncoding("UTF-8");
    response.setStatus(httpStatus.value());
    FailResponse failResponse = new FailResponse(httpStatus.value(), message);
    response.getWriter()
        .write(objectMapper.writeValueAsString(failResponse));
  }
}
