package seoultech.startapp.member.application.port.out;

import seoultech.startapp.member.domain.SmsAuth;

public interface SaveSmsAuthPort {

  void save(SmsAuth smsAuth);
}
