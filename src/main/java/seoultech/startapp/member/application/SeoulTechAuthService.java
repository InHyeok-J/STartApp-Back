package seoultech.startapp.member.application;

import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.member.application.port.in.SeoulTechAuthUseCase;
import seoultech.startapp.member.application.port.in.command.SeoulTechAuthCommand;
import seoultech.startapp.member.application.port.out.LoadMemberPort;
import seoultech.startapp.member.application.port.out.RedisCachePort;
import seoultech.startapp.member.exception.SeoulTechAuthenticationFailException;

@Slf4j
@Service
@RequiredArgsConstructor
public class SeoulTechAuthService implements SeoulTechAuthUseCase {

  private final LoadMemberPort loadMemberPort;
  private final RedisCachePort redisCachePort;

  @Transactional
  @Override
  public void authSeoulTech(SeoulTechAuthCommand command) {
    if(loadMemberPort.existByStudentNo(command.getStudentNo())) {
      log.error("이미 가입된 학번으로 실패");
      return;
    }

    redisCachePort.setStringWithTTL(command.getKey(), command.getJsonValue(), 1, TimeUnit.MINUTES);

  }


  @Transactional(readOnly = true)
  @Override
  public SeoulTechAuthResponse checkAuth(String key) {
    String savedValue = redisCachePort.findByKey(key);

    if(savedValue == null) {
      throw new SeoulTechAuthenticationFailException("인증 정보가 없습니다.");
    }

    return new SeoulTechAuthResponse(savedValue);
  }
}
