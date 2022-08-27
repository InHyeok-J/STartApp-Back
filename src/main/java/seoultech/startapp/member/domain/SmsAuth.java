package seoultech.startapp.member.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import seoultech.startapp.member.exception.ExpiredPhoneAuthCodeException;

@Getter
public class SmsAuth {

  private Long smsAuthId;

  private String phoneNo;

  private String authCode;

  private LocalDateTime smsTime;

  @Builder
  public SmsAuth(Long smsAuthId, String phoneNo, String authCode, LocalDateTime smsTime) {
    this.smsAuthId = smsAuthId;
    this.phoneNo = phoneNo;
    this.authCode = authCode;
    this.smsTime = smsTime;
  }

  public void validTime(LocalDateTime currentTime){
    LocalDateTime checkTime = currentTime.minusMinutes(3);
    if(smsTime.isBefore(checkTime)){
      throw new ExpiredPhoneAuthCodeException("기간이 만료됐습니다.");
    }
  }

}
