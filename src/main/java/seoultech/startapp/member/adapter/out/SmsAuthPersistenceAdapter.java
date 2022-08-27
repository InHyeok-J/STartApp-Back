package seoultech.startapp.member.adapter.out;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import seoultech.startapp.member.application.port.out.LoadSmsAuthPort;
import seoultech.startapp.member.application.port.out.SaveSmsAuthPort;
import seoultech.startapp.member.domain.SmsAuth;

@Component
@RequiredArgsConstructor
public class SmsAuthPersistenceAdapter implements LoadSmsAuthPort, SaveSmsAuthPort {

  private final JpaSmsAuthRepository jpaSmsAuthRepository;
  private final SmsAuthMapper smsAuthMapper;

  @Override
  public Long countByTenMin(String phoneNo) {
    LocalDateTime dateTime = LocalDateTime.now().minusMinutes(10);
    return jpaSmsAuthRepository.countBySmsTimeAfterAndPhoneNo(dateTime, phoneNo);
  }

  @Override
  public SmsAuth loadByPhoneNoAndCode(String phoneNo, String code) {
    return jpaSmsAuthRepository.findByAuthCodeAndPhoneNo(code,phoneNo)
        .map(smsAuthMapper::toDomainEntity)
        .orElse(null);
  }

  @Override
  public void save(SmsAuth smsAuth) {
    jpaSmsAuthRepository.save(smsAuthMapper.toJapEntity(smsAuth));
  }
}
