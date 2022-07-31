package seoultech.startapp.member.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import seoultech.startapp.member.application.port.in.LogoutCommand;
import seoultech.startapp.member.application.port.out.RedisCachePort;
import seoultech.startapp.member.exception.NotLoginMemberException;
import seoultech.startapp.member.exception.NotMatchLoginInfoException;


@ExtendWith(MockitoExtension.class)
class LogoutServiceTest {

  @Mock
  RedisCachePort redisCachePort;

  @InjectMocks
  LogoutService logoutService;

  @Test
  @DisplayName("로그인이 안된 유저 실패")
  public void notlogin() throws Exception {
    //given
    Long memberId = 1L;
    String token = "refresh";
    LogoutCommand command = createLogoutCommand(memberId,token);
    given(redisCachePort.findByKey(any())).willReturn(null);

    NotLoginMemberException e = assertThrows(NotLoginMemberException.class,
        () -> logoutService.logout(command));
  }

  @Test
  @DisplayName("로그인 정보 불일치")
  public void notMatchValue() throws Exception {
    //given
    Long memberId = 1L;
    String token = "refresh";
    String savedToken = "savedToken";
    LogoutCommand command = createLogoutCommand(memberId,token);
    given(redisCachePort.findByKey(any())).willReturn(savedToken);

    NotMatchLoginInfoException e = assertThrows(NotMatchLoginInfoException.class,
        () -> logoutService.logout(command));
  }

  private LogoutCommand createLogoutCommand(Long memberId, String token){
    return new LogoutCommand(memberId,token);
  }
}