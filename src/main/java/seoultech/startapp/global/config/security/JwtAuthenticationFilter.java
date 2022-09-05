package seoultech.startapp.global.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import seoultech.startapp.global.common.HeaderTokenExtractor;
import seoultech.startapp.member.application.JwtResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final HeaderTokenExtractor headerTokenExtractor;
  private final JwtResolver jwtResolver;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String jwtToken = headerTokenExtractor.extractAccessToken(request);
    if (StringUtils.hasText(jwtToken) && jwtResolver.validateAccessToken(jwtToken)) {
      Authentication authentication = jwtResolver.getAuthentication(jwtToken);
      SecurityContext context = SecurityContextHolder.createEmptyContext();
      context.setAuthentication(authentication);
      SecurityContextHolder.setContext(context);
    }
    filterChain.doFilter(request, response);
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

    List<AntPathRequestMatcher> skipPathList = new ArrayList<>();
    skipPathList.add(new AntPathRequestMatcher("/", HttpMethod.GET.name()));
    skipPathList.add(new AntPathRequestMatcher("/api/v1/member", HttpMethod.POST.name()));
    skipPathList.add(new AntPathRequestMatcher("/api/v1/member/slack**"));
    skipPathList.add(new AntPathRequestMatcher("/api/v1/auth/login", HttpMethod.POST.name()));
    skipPathList.add(new AntPathRequestMatcher("/api/v1/auth/refresh", HttpMethod.GET.name()));
    skipPathList.add(new AntPathRequestMatcher("/api/v1/event", HttpMethod.GET.name()));
    skipPathList.add(new AntPathRequestMatcher("/api/v1/plan", HttpMethod.GET.name()));
    skipPathList.add(new AntPathRequestMatcher("/api/v1/banner", HttpMethod.GET.name()));
    skipPathList.add(new AntPathRequestMatcher("/api/v1/auth/seoultech**"));
    skipPathList.add(
        new AntPathRequestMatcher("/api/v1/auth/seoultech/check**", HttpMethod.GET.name()));
    skipPathList.add(
        new AntPathRequestMatcher("/api/v1/member/duplicate**", HttpMethod.GET.name()));
    skipPathList.add(new AntPathRequestMatcher("/api/v1/auth/loading", HttpMethod.GET.name()));
    skipPathList.add(new AntPathRequestMatcher("/favicon.ico**", HttpMethod.GET.name()));
    skipPathList.add(new AntPathRequestMatcher("/api/v1/booth", HttpMethod.GET.name()));
    skipPathList.add(new AntPathRequestMatcher("/api/v1/rent/calendar", HttpMethod.GET.name()));
    skipPathList.add(
        new AntPathRequestMatcher("/api/v1/rent/item/calendar", HttpMethod.GET.name()));
    skipPathList.add(new AntPathRequestMatcher("/api/v1/suggestion**", HttpMethod.POST.name()));
    skipPathList.add(new AntPathRequestMatcher("/api/v1/auth/sms/**", HttpMethod.POST.name()));
    skipPathList.add(new AntPathRequestMatcher("/api/v1/auth/sms", HttpMethod.POST.name()));
    skipPathList.add(
        new AntPathRequestMatcher("/api/v1/auth/sms/password", HttpMethod.POST.name()));
    skipPathList.add(
        new AntPathRequestMatcher("/api/v1/auth/sms/password/check", HttpMethod.POST.name()));
    skipPathList.add(new AntPathRequestMatcher("/api/v1/member/password", HttpMethod.PATCH.name()));
    skipPathList.add(new AntPathRequestMatcher("/api/v1/festival", HttpMethod.GET.name()));

    OrRequestMatcher orRequestMatcher = new OrRequestMatcher(new ArrayList<>(skipPathList));
    return skipPathList.stream()
        .anyMatch(p -> orRequestMatcher.matches(request));
  }
}
