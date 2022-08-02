package seoultech.startapp.member.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
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

  Member savedMember;
  RefreshCommand refreshCommand;
  @BeforeEach
  void setUp() {
    this.refreshCommand = new RefreshCommand("access", "refresh");
    this.savedMember = Member.builder()
        .memberId(1L)
        .name("저장된유저")
        .studentNo("180808")
        .password("savedPassword")
        .phoneNo("010-9999-9999")
        .build();
  }

  @Test
  @DisplayName("refreshToken 잘못된 값으로 실패.")
  public void refreshTokenValueisFail() throws Exception {

    //given
    given(jwtResolver.validateRefreshToken(any())).willReturn(false);

    //When, then
    InvalidJwtException e = assertThrows(InvalidJwtException.class,
        () -> refreshService.refresh(refreshCommand));
  }

  @Test
  @DisplayName("로그인이 안된 유저, 실패")
  public void redisWillReturnNull() throws Exception {
    //given
    given(jwtResolver.validateRefreshToken(any())).willReturn(true);
    given(jwtResolver.getMemberIdByJwt(any())).willReturn(1L);

    given(redisCachePort.findByKey(any())).willReturn(null);

    //then
    NotLoginMemberException e = assertThrows(NotLoginMemberException.class,
        () -> refreshService.refresh(refreshCommand));
  }

  @Test
  @DisplayName("로그인이 됐지만, 정보 불일치")
  public void refreshFailByInvalidSavedToken() throws Exception {
    //given
    String invalidSavedRefreshToken = "savedRefresh";
    given(jwtResolver.validateRefreshToken(any())).willReturn(true);
    given(jwtResolver.getMemberIdByJwt(any())).willReturn(1L);
    given(redisCachePort.findByKey(any())).willReturn(invalidSavedRefreshToken);

    //then
    NotMatchLoginInfoException e = assertThrows(NotMatchLoginInfoException.class,
        () -> refreshService.refresh(refreshCommand));
  }

  @Test
  @DisplayName("refresh 성공")
  public void refreshSuccess() throws Exception {
    //given

    given(jwtResolver.validateRefreshToken(any())).willReturn(true);
    given(jwtResolver.getMemberIdByJwt(any())).willReturn(1L);
    given(redisCachePort.findByKey(any())).willReturn(refreshCommand.getRefreshToken());
    given(loadMemberPort.loadByMemberId(any())).willReturn(savedMember);

    String newAccessToken = "newAccess";
    given(jwtProvider.createAccessToken(any())).willReturn(newAccessToken);
    //when
    AccessToken result = refreshService.refresh(refreshCommand);

    //then
    assertEquals(newAccessToken, result.getAccessToken());
  }

}