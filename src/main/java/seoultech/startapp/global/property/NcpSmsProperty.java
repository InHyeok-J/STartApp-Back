package seoultech.startapp.global.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("sms")
public class NcpSmsProperty {
  private String accessKey;
  private String secretKey;
  private String serviceId;
  private String senderNo;
}
