package seoultech.startapp.global.config.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import seoultech.startapp.member.application.JwtResolver;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final HeaderTokenExtractor headerTokenExtractor;
  private final JwtResolver jwtResolver;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException , IOException {

    String jwtToken = headerTokenExtractor.extractToken(request);
    if(StringUtils.hasText(jwtToken) && jwtResolver.validateAccessToken(jwtToken)){
      Authentication authentication = jwtResolver.getAuthentication(jwtToken);
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    filterChain.doFilter(request,response);
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

    List<AntPathRequestMatcher> skipPathList = new ArrayList<>();
    skipPathList.add(new AntPathRequestMatcher("/", HttpMethod.GET.name()));
    skipPathList.add(new AntPathRequestMatcher("/api/member", HttpMethod.POST.name()));
    skipPathList.add(new AntPathRequestMatcher("/api/auth/login", HttpMethod.POST.name()));
    skipPathList.add(new AntPathRequestMatcher("/api/auth/refresh", HttpMethod.POST.name()));
    OrRequestMatcher orRequestMatcher = new OrRequestMatcher(new ArrayList<>(skipPathList));
    return skipPathList.stream()
        .anyMatch(p -> orRequestMatcher.matches(request));
  }
}
