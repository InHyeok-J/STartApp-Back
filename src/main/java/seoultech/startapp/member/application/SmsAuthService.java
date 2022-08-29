package seoultech.startapp.member.application;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.member.application.port.in.SmsAuthUseCase;
import seoultech.startapp.member.application.port.in.command.FindPasswordCheckCommand;
import seoultech.startapp.member.application.port.in.command.FindPasswordPushCommand;
import seoultech.startapp.member.application.port.in.command.SmsCheckCommand;
import seoultech.startapp.member.application.port.in.command.SmsPushCommand;
import seoultech.startapp.member.application.port.out.LoadMemberPort;
import seoultech.startapp.member.application.port.out.LoadSmsAuthPort;
import seoultech.startapp.member.application.port.out.RedisCachePort;
import seoultech.startapp.member.application.port.out.SaveSmsAuthPort;
import seoultech.startapp.member.application.port.out.SmsPushPort;
import seoultech.startapp.member.domain.Member;
import seoultech.startapp.member.domain.SmsAuth;
import seoultech.startapp.member.exception.AlreadyUsePhoneNoException;
import seoultech.startapp.member.exception.ManyRequestPhoneAuthException;
import seoultech.startapp.member.exception.NotMatchPhoneAuthException;

@Service
@RequiredArgsConstructor
public class SmsAuthService implements SmsAuthUseCase {

  private final SmsPushPort smsPushPort;
  private final LoadSmsAuthPort loadSmsAuthPort;
  private final SaveSmsAuthPort saveSmsAuthPort;
  private final LoadMemberPort loadMemberPort;
  private final RedisCachePort redisCachePort;

  @Transactional
  @Override
  public void signUpPrePush(SmsPushCommand command) {
    String phoneNo = command.getPhoneNo();
    if (loadMemberPort.existsByPhoneNo(phoneNo)) {
      throw new AlreadyUsePhoneNoException("이미 사용중인 휴대폰 번호입니다.");
    }

    Long requestCount = loadSmsAuthPort.countByTenMin(phoneNo);

    if (requestCount >= 5) {
      throw new ManyRequestPhoneAuthException("너무 많은 인증 요청을 보냈습니다.");
    }
    String code = randomNumberToString();
    SmsAuth smsAuth = command.toEntity(code);
    saveSmsAuthPort.save(smsAuth);
    smsPushPort.push(phoneNo, code);
  }

  @Transactional
  @Override
  public void findPasswordPush(FindPasswordPushCommand command) {
    Member member = loadMemberPort.loadByStudentNo(command.getStudentNo());
    member.canLoginValidation();
    String phoneNo = member.getProfile().getPhoneNo();
    Long requestCount = loadSmsAuthPort.countByTenMin(phoneNo);
    if (requestCount >= 5) {
      throw new ManyRequestPhoneAuthException("너무 많은 인증 요청을 보냈습니다.");
    }
    String code = randomNumberToString();
    SmsAuth smsAuth = SmsAuth.builder()
        .phoneNo(phoneNo)
        .authCode(code)
        .smsTime(LocalDateTime.now())
        .build();
    saveSmsAuthPort.save(smsAuth);
    smsPushPort.push(phoneNo, code);
  }

  @Transactional
  @Override
  public void findPasswordCheck(FindPasswordCheckCommand command) {
    Member member = loadMemberPort.loadByStudentNo(command.getStudentNo());
    SmsAuth smsAuth = loadSmsAuthPort.loadByPhoneNoAndCode(member.getProfile().getPhoneNo(),
        command.getCode());

    if (smsAuth == null) {
      throw new NotMatchPhoneAuthException("인증 정보가 일치하지 않습니다.");
    }

    smsAuth.validTime(LocalDateTime.now());
    redisCachePort.setStringWithTTL("PASSWORD-" + command.getStudentNo(), command.getCode(), 5,
        TimeUnit.MINUTES);
  }

  @Transactional(readOnly = true)
  @Override
  public void check(SmsCheckCommand command) {
    String phoneNo = command.getPhoneNo();
    String code = command.getCode();
    SmsAuth smsAuth = loadSmsAuthPort.loadByPhoneNoAndCode(phoneNo, code);
    if (smsAuth == null) {
      throw new NotMatchPhoneAuthException("인증 정보가 일치하지 않습니다.");
    }

    smsAuth.validTime(LocalDateTime.now());

    redisCachePort.setStringWithTTL("PHONE-" + phoneNo, code, 5, TimeUnit.MINUTES);
  }

  private String randomNumberToString() {
    StringBuilder randomNumber = new StringBuilder();
    for (int i = 0; i < 6; i++) {
      char ch = (char) ((int) (Math.random() * 10) + 48);
      randomNumber.append(ch);
    }
    return randomNumber.toString();
  }
}
