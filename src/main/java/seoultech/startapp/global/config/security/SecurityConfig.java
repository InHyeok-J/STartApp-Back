package seoultech.startapp.global.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import seoultech.startapp.member.domain.MemberRole;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final JwtExceptionFilter jwtExceptionFilter;
  private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    http.addFilterBefore(jwtExceptionFilter, JwtAuthenticationFilter.class);

    http.csrf().disable();

    http.sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http.exceptionHandling()
            .accessDeniedHandler(jwtAccessDeniedHandler);

    http.authorizeRequests()
        .antMatchers("/api/admin/**").access("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
        .anyRequest().permitAll();
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
}
