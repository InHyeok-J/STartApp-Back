package seoultech.startapp.member.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.will;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TokenProviderTest {

  @Mock
  JwtProvider jwtProvider;

  @InjectMocks
  TokenProvider tokenProvider;

  @Test
  @DisplayName("Token 생성 테스트 ")
  public void tokenProvider_CreateToken() throws Exception {
    //given
    Long memberId = 1L;
    MemberRole memberRole = MemberRole.MEMBER;
    Map<String, Object> payload = new HashMap<>();
    payload.put("memberId", memberId);
    payload.put("role", memberRole);
    //when
    AllToken result = tokenProvider.creatToken(memberId,memberRole);
    //then
    verify(jwtProvider, times(1)).createAccessToken(payload);
    verify(jwtProvider,times(1)).createRefreshToken();
  }
}