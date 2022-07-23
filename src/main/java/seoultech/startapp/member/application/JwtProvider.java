package seoultech.startapp.member.application;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.springframework.stereotype.Component;
import seoultech.startapp.global.property.JwtProperty;
import seoultech.startapp.member.domain.TokenInfo;

@Component
public class JwtProvider {

  private final Key accesskey;
  private final Key refreshKey;
  private final Integer accessExpired;
  private final Integer refreshExpired;

  public JwtProvider(JwtProperty jwtProperty) {
    byte[] accessKeyBytes = jwtProperty.getAccessKey().getBytes(StandardCharsets.UTF_8);
    byte[] refreshKeyBytes = jwtProperty.getRefreshKey().getBytes(StandardCharsets.UTF_8);
    this.accesskey = Keys.hmacShaKeyFor(accessKeyBytes);
    this.refreshKey = Keys.hmacShaKeyFor(refreshKeyBytes);
    this.accessExpired = jwtProperty.getAccessExpiredMin();
    this.refreshExpired = jwtProperty.getRefreshExpiredDay();
  }

  public String createAccessToken(TokenInfo tokenInfo){
    return Jwts.builder()
        .addClaims(tokenInfo.getPayload())
        .setExpiration(Date.from(Instant.now().plus(accessExpired,ChronoUnit.MINUTES)))
        .signWith(accesskey)
        .compact();
  }

  public String createRefreshToken(){
    return Jwts.builder()
        .setExpiration(Date.from(Instant.now().plus(refreshExpired, ChronoUnit.DAYS)))
        .signWith(refreshKey)
        .compact();
  }
}
