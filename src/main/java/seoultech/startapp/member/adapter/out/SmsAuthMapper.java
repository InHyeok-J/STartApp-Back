package seoultech.startapp.member.adapter.out;

import org.springframework.stereotype.Component;
import seoultech.startapp.member.domain.SmsAuth;

@Component
public class SmsAuthMapper {

  public SmsAuth toDomainEntity(JpaSmsAuth jpaSmsAuth){
    return SmsAuth.builder()
        .smsAuthId(jpaSmsAuth.getId())
        .phoneNo(jpaSmsAuth.getPhoneNo())
        .authCode(jpaSmsAuth.getAuthCode())
        .smsTime(jpaSmsAuth.getSmsTime())
        .build();
  }

  public JpaSmsAuth toJapEntity(SmsAuth smsAuth){
    return JpaSmsAuth.builder()
        .id(smsAuth.getSmsAuthId() == null ? null : smsAuth.getSmsAuthId())
        .phoneNo(smsAuth.getPhoneNo())
        .authCode(smsAuth.getAuthCode())
        .smsTime(smsAuth.getSmsTime())
        .build();
  }
}
