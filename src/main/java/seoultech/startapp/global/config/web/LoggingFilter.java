package seoultech.startapp.global.config.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;
import seoultech.startapp.global.common.AccessLogDto;


@Slf4j
public class LoggingFilter extends OncePerRequestFilter {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    AccessLogDto logDto = AccessLogDto.from(request);
    log.info(objectMapper.writeValueAsString(logDto));
    filterChain.doFilter(request, response);
  }
}

