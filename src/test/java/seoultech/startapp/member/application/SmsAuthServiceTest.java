package seoultech.startapp.member.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import seoultech.startapp.member.application.port.in.command.FindPasswordCheckCommand;
import seoultech.startapp.member.application.port.in.command.FindPasswordPushCommand;
import seoultech.startapp.member.application.port.in.command.SmsCheckCommand;
import seoultech.startapp.member.application.port.in.command.SmsPushCommand;
import seoultech.startapp.member.application.port.out.LoadMemberPort;
import seoultech.startapp.member.application.port.out.LoadSmsAuthPort;
import seoultech.startapp.member.application.port.out.RedisCachePort;
import seoultech.startapp.member.application.port.out.SaveSmsAuthPort;
import seoultech.startapp.member.application.port.out.SmsPushPort;
import seoultech.startapp.member.domain.Member;
import seoultech.startapp.member.domain.MemberProfile;
import seoultech.startapp.member.domain.MemberStatus;
import seoultech.startapp.member.domain.SmsAuth;
import seoultech.startapp.member.exception.AlreadyUsePhoneNoException;
import seoultech.startapp.member.exception.ExpiredPhoneAuthCodeException;
import seoultech.startapp.member.exception.LeaveMemberException;
import seoultech.startapp.member.exception.ManyRequestPhoneAuthException;
import seoultech.startapp.member.exception.NotMatchPhoneAuthException;
import seoultech.startapp.member.exception.RequireCardAuthException;

@ExtendWith(MockitoExtension.class)
class SmsAuthServiceTest {

  @Mock
  SmsPushPort smsPushPort;

  @Mock
  LoadSmsAuthPort loadSmsAuthPort;

  @Mock
  SaveSmsAuthPort saveSmsAuthPort;

  @Mock
  LoadMemberPort loadMemberPort;

  @Mock
  RedisCachePort redisCachePort;

  @InjectMocks
  SmsAuthService smsAuthService;

  SmsPushCommand pushCommand;
  SmsCheckCommand smsCheckCommand;
  FindPasswordPushCommand findPasswordPushCommand;
  FindPasswordCheckCommand findPasswordCheckCommand;

  @BeforeEach
  void setUp() {
    pushCommand = new SmsPushCommand("010-2642-2713");
    smsCheckCommand = new SmsCheckCommand("010-2642-2713", "123456");
    findPasswordPushCommand = new FindPasswordPushCommand("studentNo");
    findPasswordCheckCommand = new FindPasswordCheckCommand("studentNo", "123456");
  }


  @Test
  @DisplayName("인증 요청시 이미 사용중인 번호인 경우")
  public void smspush_already_phoneNo() throws Exception {
    given(loadMemberPort.existsByPhoneNo(pushCommand.getPhoneNo())).willReturn(true);

    assertThrows(AlreadyUsePhoneNoException.class, () ->
        smsAuthService.signUpPrePush(pushCommand));
  }

  @Test
  @DisplayName("10분 이내 보낸 요청이 5이상인 경우 실패.")
  public void smspush_toomany_request() throws Exception {
    given(loadMemberPort.existsByPhoneNo(pushCommand.getPhoneNo())).willReturn(false);
    given(loadSmsAuthPort.countByTenMin(pushCommand.getPhoneNo())).willReturn(5L);

    assertThrows(ManyRequestPhoneAuthException.class, () ->
        smsAuthService.signUpPrePush(pushCommand));
  }

  @Test
  @DisplayName("인증 요청 보내기 성공")
  public void smspush_success() throws Exception {
    given(loadMemberPort.existsByPhoneNo(pushCommand.getPhoneNo())).willReturn(false);
    given(loadSmsAuthPort.countByTenMin(pushCommand.getPhoneNo())).willReturn(3L);
    smsAuthService.signUpPrePush(pushCommand);

    verify(saveSmsAuthPort, times(1)).save(any());
    verify(smsPushPort, times(1)).push(any(), any());
  }

  @Test
  @DisplayName("인증 확인시 저장된 정보가 없음.")
  public void sms_check_fail_not_saveAuth() throws Exception {
    given(loadSmsAuthPort.loadByPhoneNoAndCode(any(), any())).willReturn(null);

    assertThrows(NotMatchPhoneAuthException.class, () ->
        smsAuthService.check(smsCheckCommand));
  }

  @Test
  @DisplayName("인증 확인시 만료기간이 지남.")
  public void sms_check_expired() throws Exception {
    SmsAuth expiredAuth = SmsAuth.builder()
        .smsAuthId(1L)
        .authCode("123456")
        .smsTime(LocalDateTime.now().minusMinutes(4))
        .build();
    given(loadSmsAuthPort.loadByPhoneNoAndCode(any(), any())).willReturn(expiredAuth);

    assertThrows(ExpiredPhoneAuthCodeException.class, () ->
        smsAuthService.check(smsCheckCommand));
  }

  @Test
  @DisplayName("인증 확인 성공")
  public void sms_check_success() throws Exception {
    SmsAuth expiredAuth = SmsAuth.builder()
        .smsAuthId(1L)
        .authCode("123456")
        .smsTime(LocalDateTime.now().minusMinutes(2))
        .build();
    given(loadSmsAuthPort.loadByPhoneNoAndCode(any(), any())).willReturn(expiredAuth);

    smsAuthService.check(smsCheckCommand);

    verify(redisCachePort, times(1)).setStringWithTTL(any(), any(), any(), any());
  }

  @Test
  @DisplayName("패스워드 찾기 요청시 학생증 인증 전 회원이면 실패")
  public void findPassword_fail_precardauth() throws Exception {
    Member member = Member.builder()
        .memberId(1L)
        .memberStatus(MemberStatus.PRE_CARD_AUTH)
        .memberProfile(MemberProfile.builder().studentNo("studentNo").build())
        .build();

    given(loadMemberPort.loadByStudentNo("studentNo")).willReturn(member);

    assertThrows(RequireCardAuthException.class,
        () -> smsAuthService.findPasswordPush(findPasswordPushCommand));
  }

  @Test
  @DisplayName("패스워드 찾기 요청시 탈퇴한 회원이면 X")
  public void findPassword_fail_leaveCardAuth() throws Exception {
    Member member = Member.builder()
        .memberId(1L)
        .memberStatus(MemberStatus.LEAVE)
        .memberProfile(MemberProfile.builder().studentNo("studentNo").build())
        .build();

    given(loadMemberPort.loadByStudentNo("studentNo")).willReturn(member);

    assertThrows(LeaveMemberException.class,
        () -> smsAuthService.findPasswordPush(findPasswordPushCommand));
  }

  @Test
  @DisplayName("패스워드 찾기 문자 요청 성공")
  public void findPassword_push_success() throws Exception {
    Member member = Member.builder()
        .memberId(1L)
        .memberStatus(MemberStatus.POST_CARD_AUTH)
        .memberProfile(MemberProfile.builder().studentNo("studentNo").build())
        .build();

    given(loadMemberPort.loadByStudentNo("studentNo")).willReturn(member);
    given(loadSmsAuthPort.countByTenMin(any())).willReturn(3L);

    smsAuthService.findPasswordPush(findPasswordPushCommand);

    verify(saveSmsAuthPort, times(1)).save(any());
    verify(smsPushPort, times(1)).push(any(), any());
  }

  @Test
  @DisplayName("패스워드 찾기 문자 확인 성공")
  public void findPassword_check_success() throws Exception {
    Member member = Member.builder()
        .memberId(1L)
        .memberStatus(MemberStatus.POST_CARD_AUTH)
        .memberProfile(MemberProfile.builder().studentNo("studentNo").build())
        .build();

    given(loadMemberPort.loadByStudentNo("studentNo")).willReturn(member);
    given(loadSmsAuthPort.loadByPhoneNoAndCode(any(), any())).willReturn(SmsAuth.builder()
            .smsAuthId(1L)
            .phoneNo("010-2642-2713")
            .smsTime(LocalDateTime.now().minusMinutes(1))
        .build());

    smsAuthService.findPasswordCheck(findPasswordCheckCommand);

    verify(redisCachePort, times(1)).setStringWithTTL(
        "PASSWORD-" + findPasswordCheckCommand.getStudentNo(),
        findPasswordCheckCommand.getCode(), 5,
        TimeUnit.MINUTES);
  }
}