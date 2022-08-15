package seoultech.startapp.member.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import seoultech.startapp.member.application.port.in.command.SeoulTechAuthCommand;
import seoultech.startapp.member.application.port.out.LoadMemberPort;
import seoultech.startapp.member.application.port.out.RedisCachePort;
import seoultech.startapp.member.exception.SeoulTechAuthenticationFailException;


@ExtendWith(MockitoExtension.class)
class SeoulTechAuthServiceTest {

  @Mock
  LoadMemberPort loadMemberPort;

  @Mock
  RedisCachePort redisCachePort;

  @InjectMocks
  SeoulTechAuthService seoulTechAuthService;

  SeoulTechAuthCommand seoulTechAuthCommand;

  @BeforeEach
  void setUp() {
    seoulTechAuthCommand = SeoulTechAuthCommand.builder()
        .jsonValue("jsonvalue")
        .key("key")
        .studentNo("studentNo")
        .build();
  }


  @Test
  @DisplayName("이미 가입된 학번 실패")
  public void alreadyRegistered() throws Exception {
    //given
    given(loadMemberPort.existByStudentNo(any())).willReturn(true);
    seoulTechAuthService.authSeoulTech(seoulTechAuthCommand);
    verify(redisCachePort, times(0)).setStringWithTTL(any(), any(), any(), any());
  }

  @Test
  @DisplayName("인증 성공 및 저장")
  public void authSuccess() throws Exception {
    given(loadMemberPort.existByStudentNo(any())).willReturn(false);
    seoulTechAuthService.authSeoulTech(seoulTechAuthCommand);
    verify(redisCachePort, times(1)).setStringWithTTL(any(), any(), any(), any());
  }

  @Test
  @DisplayName("인증 정보가 저장이 안된 경우")
  public void checkAuthFail() throws Exception {
    given(redisCachePort.findByKey("key")).willReturn(null);
    assertThrows(SeoulTechAuthenticationFailException.class,
        () -> seoulTechAuthService.checkAuth("key"));
  }

  @Test
  @DisplayName("인증 확인 성공")
  public void checkAuthSuccess() throws Exception {
    String saveKey = "key123";
    given(redisCachePort.findByKey("key")).willReturn(saveKey);

    SeoulTechAuthResponse result = seoulTechAuthService.checkAuth("key");

    assertEquals(result.jsonValue(), saveKey);
  }
}