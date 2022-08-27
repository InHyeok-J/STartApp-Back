package seoultech.startapp.member.adapter.out;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "sms_auth")
@Getter
@NoArgsConstructor
public class JpaSmsAuth {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "sms_auth_id")
  private Long id;

  @Column(nullable = false)
  private String phoneNo;

  @Column(name = "auth_code")
  private String authCode;

  @Column(name = "sms_time", nullable = false)
  private LocalDateTime smsTime;

  @Builder
  public JpaSmsAuth(Long id, String phoneNo, String authCode, LocalDateTime smsTime) {
    this.id = id;
    this.phoneNo = phoneNo;
    this.authCode = authCode;
    this.smsTime = smsTime;
  }
}
