package seoultech.startapp.member.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import seoultech.startapp.member.application.port.out.LoadMemberPort;
import seoultech.startapp.member.application.port.out.SaveMemberPort;
import seoultech.startapp.member.application.port.out.SmsPushPort;
import seoultech.startapp.member.domain.Member;
import seoultech.startapp.member.domain.MemberProfile;
import seoultech.startapp.member.domain.MemberStatus;
import seoultech.startapp.member.exception.AlreadyCardAuthException;

@ExtendWith(MockitoExtension.class)
class StudentCardAuthServiceTest {

  @Mock
  LoadMemberPort loadMemberPort;

  @Mock
  SaveMemberPort saveMemberPort;
  @Mock
  SmsPushPort smsPushPort;
  @InjectMocks
  StudentCardAuthService studentCardAuthService;

  final Long memberId = 1L;

  @Test
  @DisplayName("이미 학생증 인증이 돼서 실패")
  public void alreadyCardAuth() throws Exception {
    Member alreadyCardAuthMember = Member.builder()
        .memberId(memberId)
        .memberStatus(MemberStatus.POST_CARD_AUTH)
        .build();

    given(loadMemberPort.loadByMemberId(memberId)).willReturn(alreadyCardAuthMember);

    assertThrows(AlreadyCardAuthException.class,
        () -> studentCardAuthService.cardAuth(memberId));
  }

  @Test
  @DisplayName("학생증 인증 성공")
  public void cardAuthSuccess() throws Exception {
    Member requireCardAuthMember = Member.builder()
        .memberId(memberId)
        .memberStatus(MemberStatus.PRE_CARD_AUTH)
        .memberProfile(MemberProfile.builder()
            .phoneNo("010-2642-2713")
            .build())
        .build();

    given(loadMemberPort.loadByMemberId(memberId)).willReturn(requireCardAuthMember);

    studentCardAuthService.cardAuth(memberId);

    verify(saveMemberPort, times(1)).save(requireCardAuthMember);
  }
}