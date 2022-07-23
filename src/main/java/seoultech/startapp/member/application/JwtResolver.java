package seoultech.startapp.member.application;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import seoultech.startapp.global.exception.AuthenticationFailException;
import seoultech.startapp.global.property.JwtProperty;

@Slf4j
@Component
public class JwtResolver {

  private final Key accessKey;
  private final Key refreshKey;

  public JwtResolver(JwtProperty jwtProperty) {
    byte[] accessKeyBytes = jwtProperty.getAccessKey().getBytes(StandardCharsets.UTF_8);
    byte[] refreshKeyBytes = jwtProperty.getRefreshKey().getBytes(StandardCharsets.UTF_8);
    this.accessKey = Keys.hmacShaKeyFor(accessKeyBytes);
    this.refreshKey = Keys.hmacShaKeyFor(refreshKeyBytes);
  }

  public Authentication getAuthentication(String token) {
    Claims claims = Jwts.parserBuilder().setSigningKey(accessKey).build().parseClaimsJws(token)
        .getBody();
    String memberId = claims.get("memberId").toString();
    String role = claims.get("role").toString();

    Collection<? extends GrantedAuthority> authorities = new ArrayList<>(
        List.of(new SimpleGrantedAuthority(role)));

    return new UsernamePasswordAuthenticationToken(memberId, "", authorities);
  }


  public boolean validateAccessToken(String token){
    try{
      Jwts.parserBuilder().setSigningKey(accessKey).build().parse(token);
      return true;
    } catch (SecurityException | MalformedJwtException | SignatureException e){
      log.error("잘못된 JWT 서명");
    } catch (UnsupportedJwtException e){
      log.error("지원하지 않는 JWT 토큰");
    } catch (IllegalArgumentException e){
      log.error("잘못된 토큰 값 ");
    } catch (ExpiredJwtException e) {
      log.error("잘못된 JWT 서명");
    }
    throw new AuthenticationFailException( "jwt 인증 실패", HttpStatus.UNAUTHORIZED);
  }

  public boolean validateRefreshToken(String refreshToken){
    try{
      Jwts.parserBuilder().setSigningKey(refreshKey).build().parse(refreshToken);
      return true;
    }catch (Exception e){
      return false;
    }
  }

  public String getMemberIdByJwt(String token){
    try{
      Claims claims = Jwts.parserBuilder().setSigningKey(accessKey).build().parseClaimsJws(token)
          .getBody();
      return claims.get("memberId").toString();
    }catch (ExpiredJwtException e ){
      return e.getClaims().get("memberId").toString();
    } catch (Exception e ){
      throw new AuthenticationFailException("jwt 인증 실패", HttpStatus.UNAUTHORIZED);
    }
  }
}
