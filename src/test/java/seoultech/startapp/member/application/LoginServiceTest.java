package seoultech.startapp.member.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import seoultech.startapp.global.property.JwtProperty;
import seoultech.startapp.member.application.port.in.LoginCommand;
import seoultech.startapp.member.application.port.out.LoadMemberPort;
import seoultech.startapp.member.application.port.out.RedisCachePort;
import seoultech.startapp.member.domain.Member;
import seoultech.startapp.member.domain.MemberRole;
import seoultech.startapp.member.domain.StudentStatus;
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

  @Test
  @DisplayName("로그인시 패스워드 불일치")
  public void loginNotMatchPasswordFail() throws Exception {
    //given
    String studentNo = "17101245";
    String inputPassword = "qwer1234";
    String savedPassword = "qwer12345";
    LoginCommand command = createLoginCommand(studentNo,inputPassword);
    Member member = createMockMember(studentNo, savedPassword, 1L);
    given(loadMemberPort.loadByStudentNo(studentNo)).willReturn(member);
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
    String inputPassword = "qwer1234";
    String savedPassword = "qwer1234";
    LoginCommand command = createLoginCommand(studentNo,inputPassword);
    Member member = createMockMember(studentNo, savedPassword,1L);
    String accessToken = "accessToken";
    String refreshToken = "refreshToke";
    given(loadMemberPort.loadByStudentNo(studentNo)).willReturn(member);
    given(jwtProvider.createAccessToken(any()))
        .willReturn(accessToken);
    given(jwtProvider.createRefreshToken()).willReturn(refreshToken);
    //when
    AllToken result = loginService.login(command);
    //then
    assertEquals(accessToken, result.getAccessToken());
    assertEquals(refreshToken, result.getRefreshToken());
  }

  private LoginCommand createLoginCommand(String studentNo, String password) {
    return new LoginCommand(studentNo, password);
  }

  private Member createMockMember(String studentNo, String password,Long memberId) {
    return new Member.Builder(studentNo, "이름", password, "컴공", "010-9999-0000",
        StudentStatus.STUDENT,MemberRole.MEMBER)
        .setMemberId(memberId)
        .build();
  }
}