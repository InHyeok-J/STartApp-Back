package seoultech.startapp.global.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("s3")
public class AwsS3Property {

  private String accessKey;
  private String secretKey;
  private String bucket;
  private String region;
  private String bucketUrl;
}
