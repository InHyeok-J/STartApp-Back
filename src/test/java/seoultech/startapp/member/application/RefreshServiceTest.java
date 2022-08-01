package seoultech.startapp.member.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import seoultech.startapp.global.exception.InvalidJwtException;
import seoultech.startapp.member.application.port.in.command.RefreshCommand;
import seoultech.startapp.member.application.port.out.LoadMemberPort;
import seoultech.startapp.member.application.port.out.RedisCachePort;
import seoultech.startapp.member.domain.Member;
import seoultech.startapp.member.domain.MemberRole;
import seoultech.startapp.member.domain.StudentStatus;
import seoultech.startapp.member.exception.NotLoginMemberException;
import seoultech.startapp.member.exception.NotMatchLoginInfoException;

@ExtendWith(MockitoExtension.class)
class RefreshServiceTest {

  @Mock
  LoadMemberPort loadMemberPort;

  @Mock
  RedisCachePort redisCachePort;

  @Mock
  JwtResolver jwtResolver;

  @Mock
  JwtProvider jwtProvider;

  @InjectMocks
  RefreshService refreshService;

  @Test
  @DisplayName("refreshToken 잘못된 값으로 실패.")
  public void refreshTokenValueisFail() throws Exception {
    //given
    String accessToken = "access";
    String refresh = "refresh";
    RefreshCommand command = createRefreshCommand(accessToken,refresh);
    //when
    given(jwtResolver.validateRefreshToken(refresh)).willReturn(false);

    //then
    InvalidJwtException e = assertThrows(InvalidJwtException.class,
        () -> refreshService.refresh(command));
  }

  @Test
  @DisplayName("로그인이 안된 유저, 실패")
  public void redisWillReturnNull() throws Exception {
    //given
    String accessToken = "access";
    String refresh = "refresh";
    RefreshCommand command = createRefreshCommand(accessToken,refresh);
    //when
    given(jwtResolver.validateRefreshToken(refresh)).willReturn(true);
    given(jwtResolver.getMemberIdByJwt(accessToken)).willReturn(1L);
    given(redisCachePort.findByKey(any())).willReturn(null);

    //then
    NotLoginMemberException e = assertThrows(NotLoginMemberException.class,
        () -> refreshService.refresh(command));
  }

  @Test
  @DisplayName("로그인이 됐지만, 정보 불일치")
  public void refreshFailByInvalidSavedToken() throws Exception {
    //given
    String accessToken = "access";
    String refresh = "refresh";
    String savedRefresh = "savedRefresh";
    RefreshCommand command = createRefreshCommand(accessToken,refresh);
    //when
    given(jwtResolver.validateRefreshToken(refresh)).willReturn(true);
    given(jwtResolver.getMemberIdByJwt(accessToken)).willReturn(1L);
    given(redisCachePort.findByKey(any())).willReturn(savedRefresh);

    //then
    NotMatchLoginInfoException e = assertThrows(NotMatchLoginInfoException.class,
        () -> refreshService.refresh(command));
  }

  @Test
  @DisplayName("refresh 성공")
  public void refreshSuccess() throws Exception {
    //given
    String accessToken = "access";
    String refresh = "refresh";
    RefreshCommand command = createRefreshCommand(accessToken,refresh);
    given(jwtResolver.validateRefreshToken(refresh)).willReturn(true);
    given(jwtResolver.getMemberIdByJwt(accessToken)).willReturn(1L);
    given(redisCachePort.findByKey(any())).willReturn(refresh);
    given(loadMemberPort.loadByMemberId(any())).willReturn(createMockMember());
    String newAccessToken = "newAccess";
    given(jwtProvider.createAccessToken(any())).willReturn(newAccessToken);
    //when
    AccessToken result = refreshService.refresh(command);

    //then
    assertEquals(newAccessToken, result.getAccessToken());
  }

  private RefreshCommand createRefreshCommand(String accessToken, String refreshToken){
    return new RefreshCommand(accessToken, refreshToken);
  }
  private Member createMockMember() {
    return new Member.Builder("studentNo", "이름", "password", "컴공", "010-9999-0000",
        StudentStatus.STUDENT, MemberRole.MEMBER)
        .setMemberId(1L)
        .build();
  }
}