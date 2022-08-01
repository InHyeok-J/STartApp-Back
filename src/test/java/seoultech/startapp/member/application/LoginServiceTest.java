package seoultech.startapp.member.application;

import static org.junit.jupiter.api.Assertions.*;
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
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import seoultech.startapp.global.property.JwtProperty;
import seoultech.startapp.member.application.port.in.command.LoginCommand;
import seoultech.startapp.member.application.port.out.LoadMemberPort;
import seoultech.startapp.member.application.port.out.RedisCachePort;
import seoultech.startapp.member.domain.Member;
import seoultech.startapp.member.domain.TokenInfo;
import seoultech.startapp.member.exception.NotMatchPasswordException;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

  @Mock
  LoadMemberPort loadMemberPort;

  @Mock
  JwtProvider jwtProvider;

  @Spy
  JwtProperty jwtProperty;

  @Mock
  RedisCachePort redisCachePort;

  @InjectMocks
  LoginService loginService;

  Member savedMember;

  @BeforeEach
  void setUp(){
    this.savedMember = Member.builder()
        .memberId(1L)
        .name("저장된유저")
        .studentNo("180808")
        .password("savedPassword")
        .phoneNo("010-9999-9999")
        .build();
  }

  @Test
  @DisplayName("로그인시 패스워드 불일치")
  public void loginNotMatchPasswordFail() throws Exception {
    //given
    String studentNo = "17101245";
    String invalidPassword = "invalidPassword";

    LoginCommand command = createLoginCommand(studentNo,invalidPassword);

    given(loadMemberPort.loadByStudentNo(studentNo)).willReturn(savedMember);
    //when
    NotMatchPasswordException e = assertThrows(NotMatchPasswordException.class, ()->
          loginService.login(command));
    //then
    assertEquals( 409,e.getErrorType().getStatusCode());
  }

  @Test
  @DisplayName("로그인 성공후 토큰 발급")
  public void loginSuccess() throws Exception {
    //given
    String studentNo = "17101245";
    String inputPassword = "savedPassword";
    LoginCommand command = createLoginCommand(studentNo,inputPassword);

    given(loadMemberPort.loadByStudentNo(studentNo)).willReturn(savedMember);

    //when
    AllToken result = loginService.login(command);
    //then
    verify(jwtProvider, times(1)).createAccessToken(any(TokenInfo.class));
    verify(jwtProvider,times(1)).createRefreshToken();
    verify(redisCachePort,times(1)).setStringWithDayTTL(any(),any(),any());
  }

  private LoginCommand createLoginCommand(String studentNo, String password) {
    return new LoginCommand(studentNo, password);
  }
}