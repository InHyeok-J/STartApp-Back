package seoultech.startapp.member.domain;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import org.springframework.stereotype.Component;
import seoultech.startapp.global.property.JwtProperty;

@Component
public class JwtProvider {

  private final Key accesskey;
  private final Key refreshKey;
  private final Integer accessExpiredMin;
  private final Integer refreshExpiredDay;

  public JwtProvider(JwtProperty jwtProperty) {
    byte[] accessKeyBytes = jwtProperty.getAccessKey().getBytes(StandardCharsets.UTF_8);
    byte[] refreshKeyBytes = jwtProperty.getRefreshKey().getBytes(StandardCharsets.UTF_8);
    this.accesskey = Keys.hmacShaKeyFor(accessKeyBytes);
    this.refreshKey = Keys.hmacShaKeyFor(refreshKeyBytes);
    this.accessExpiredMin = jwtProperty.getAccessExpiredMin();
    this.refreshExpiredDay = jwtProperty.getRefreshExpiredDay();
  }

  public String createAccessToken(Map<String, Object> payload){
    return Jwts.builder()
        .addClaims(payload)
        .setExpiration(Date.from(Instant.now().plus(accessExpiredMin, ChronoUnit.MINUTES)))
        .signWith(accesskey)
        .compact();
  }

  public String createRefreshToken(){
    return Jwts.builder()
        .setExpiration(Date.from(Instant.now().plus(refreshExpiredDay, ChronoUnit.DAYS)))
        .signWith(refreshKey)
        .compact();
  }
}
