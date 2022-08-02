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
import org.mockito.junit.jupiter.MockitoExtension;
import seoultech.startapp.member.application.port.in.command.LogoutCommand;
import seoultech.startapp.member.application.port.out.RedisCachePort;
import seoultech.startapp.member.domain.Member;
import seoultech.startapp.member.exception.NotLoginMemberException;
import seoultech.startapp.member.exception.NotMatchLoginInfoException;


@ExtendWith(MockitoExtension.class)
class LogoutServiceTest {

  @Mock
  RedisCachePort redisCachePort;

  @InjectMocks
  LogoutService logoutService;

  final Long memberId = 1L;
  final String inputRefreshToken = "refresh";
  LogoutCommand logoutCommand;

  @BeforeEach
  void setUp(){
    this.logoutCommand = new LogoutCommand(memberId, inputRefreshToken);
  }


  @Test
  @DisplayName("로그인이 안된 유저 실패")
  public void notlogin() throws Exception {
    //given
    given(redisCachePort.findByKey(any())).willReturn(null);

    NotLoginMemberException e = assertThrows(NotLoginMemberException.class,
        () -> logoutService.logout(logoutCommand));
  }

  @Test
  @DisplayName("로그인 정보 불일치")
  public void notMatchValue() throws Exception {
    //given
    String invalidRefresh = "invalidRefresh";
    given(redisCachePort.findByKey(any())).willReturn(invalidRefresh);

    NotMatchLoginInfoException e = assertThrows(NotMatchLoginInfoException.class,
        () -> logoutService.logout(logoutCommand));
  }

  @Test
  @DisplayName("로그아웃 성공")
  public void successLogout() throws Exception {
    //given
    given(redisCachePort.findByKey(any())).willReturn(inputRefreshToken);
    //when
    logoutService.logout(logoutCommand);
    verify(redisCachePort,times(1)).deleteByKey(memberId.toString());
  }

}