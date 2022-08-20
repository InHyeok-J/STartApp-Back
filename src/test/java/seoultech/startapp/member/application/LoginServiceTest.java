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
import org.springframework.security.crypto.password.PasswordEncoder;
import seoultech.startapp.global.property.JwtProperty;
import seoultech.startapp.helper.domain.MockDomainMember;
import seoultech.startapp.member.application.port.in.command.LoginCommand;
import seoultech.startapp.member.application.port.out.LoadMemberPort;
import seoultech.startapp.member.application.port.out.RedisCachePort;
import seoultech.startapp.member.domain.Member;
import seoultech.startapp.member.domain.MemberStatus;
import seoultech.startapp.member.domain.TokenInfo;
import seoultech.startapp.member.exception.LeaveMemberException;
import seoultech.startapp.member.exception.NotMatchPasswordException;
import seoultech.startapp.member.exception.RequireCardAuthException;

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

  @Mock
  PasswordEncoder passwordEncoder;

  @InjectMocks
  LoginService loginService;

  Member preCardAuthMember;
  Member postCardAuthMember;
  Member leaveMember;
  LoginCommand loginCommand;
  @BeforeEach
  void setUp(){
    LoginCommand command =new LoginCommand("1701256","inputPassword");
    this.loginCommand = command;
    this.preCardAuthMember =
        MockDomainMember.generalMockMemberByMemberStauts(MemberStatus.PRE_CARD_AUTH);
    this.postCardAuthMember =
        MockDomainMember.generalMockMemberByMemberStauts(MemberStatus.POST_CARD_AUTH);
    this.leaveMember=
        MockDomainMember.generalMockMemberByMemberStauts(MemberStatus.LEAVE);
  }

  @Test
  @DisplayName("로그인시 학생증 미인증 회원")
  public void loginNotCardAuthMember() throws Exception {
    //given
    given(loadMemberPort.loadByStudentNo(any())).willReturn(preCardAuthMember);

    assertThrows(RequireCardAuthException.class, ()->
          loginService.login(loginCommand));
  }

  @Test
  @DisplayName("로그인 시 이미 탈퇴한 회원으로 실패")
  public void loingLeaveMember() throws Exception {
    //given
    given(loadMemberPort.loadByStudentNo(any())).willReturn(leaveMember);

    assertThrows(LeaveMemberException.class, ()->
        loginService.login(loginCommand));
  }


  @Test
  @DisplayName("로그인 성공후 토큰 발급")
  public void loginSuccess() throws Exception {
    //given
    given(passwordEncoder.matches(any(),any())).willReturn(true);
    given(loadMemberPort.loadByStudentNo(any())).willReturn(postCardAuthMember);

    //when
    AllToken result = loginService.login(loginCommand);
    //then
    verify(jwtProvider, times(1)).createAccessToken(any(TokenInfo.class));
    verify(jwtProvider,times(1)).createRefreshToken();
    verify(redisCachePort,times(1)).setStringWithTTL(any(),any(),any(),any());
  }

}