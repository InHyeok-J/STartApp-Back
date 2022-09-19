package seoultech.startapp.member.application.port.out;

import seoultech.startapp.member.domain.SmsAuth;

public interface LoadSmsAuthPort {

  Long countByTenMin(String phoneNo);
  SmsAuth loadLastAuthByPhoneNo(String phoneNo);
}
