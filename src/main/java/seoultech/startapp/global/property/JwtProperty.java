package seoultech.startapp.global.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("jwt")
public class JwtProperty {

  private String accessKey;
  private String refreshKey;
  private Integer accessExpiredMin;
  private Integer refreshExpiredDay;
}
