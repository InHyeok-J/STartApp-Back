package seoultech.startapp.global.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("slack")
public class SlackProperty {
  private String token;
  private String studentCardChannel;
  private String webhookUrl;
}
