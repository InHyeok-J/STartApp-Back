package seoultech.startapp.member.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import seoultech.startapp.member.application.port.in.command.MemberPasswordChangeCommand;
import seoultech.startapp.member.application.port.in.command.NotLoginPasswordChangeCommand;
import seoultech.startapp.member.application.port.out.LoadMemberPort;
import seoultech.startapp.member.application.port.out.RedisCachePort;
import seoultech.startapp.member.application.port.out.SaveMemberPort;
import seoultech.startapp.member.domain.Member;
import seoultech.startapp.member.exception.NotMatchPasswordException;
import seoultech.startapp.member.exception.NotMatchPhoneAuthException;

@ExtendWith(MockitoExtension.class)
class PasswordServiceTest {

  @Mock
  LoadMemberPort loadMemberPort;
  @Mock
  SaveMemberPort saveMemberPort;
  @Mock
  RedisCachePort redisCachePort;
  @Mock
  PasswordEncoder passwordEncoder;

  @InjectMocks
  PasswordService passwordService;

  String studentNo = "180000";
  NotLoginPasswordChangeCommand notLoginPasswordChangeCommand;
  MemberPasswordChangeCommand passwordChangeCommand;

  @BeforeEach
  void setUp() {
    notLoginPasswordChangeCommand = NotLoginPasswordChangeCommand.builder()
        .password("qwer1234")
        .studentNo(studentNo)
        .build();
    passwordChangeCommand = MemberPasswordChangeCommand.builder()
        .memberId(1L)
        .currentPassword("qwer1234")
        .newPassword("qwer1234!")
        .build();
  }

  @Test
  @DisplayName("패스워드 찾기 후 변경 시 휴대폰 인증이 안 이루어진 경우")
  public void notLoginPasswordChange_notAuth() throws Exception {
    given(redisCachePort.findByKey(any())).willReturn(null);
    assertThrows(NotMatchPhoneAuthException.class,
        () -> passwordService.notLoginPasswordChange(notLoginPasswordChangeCommand));
  }

  @Test
  @DisplayName("패스워드 찾기 후 변경 성공")
  public void notLoginPasswordChange_success() throws Exception {
    Member member = Member.builder().memberId(1L).password("qwer1234")
        .build();

    given(redisCachePort.findByKey(any())).willReturn("anyValue");
    given(loadMemberPort.loadByStudentNo(studentNo)).willReturn(member);
    given(passwordEncoder.encode(any())).willReturn("changePassword");

    passwordService.notLoginPasswordChange(notLoginPasswordChangeCommand);

    assertEquals(member.getPassword(), "changePassword");
  }

  @Test
  @DisplayName("회원 패스워드 변경시 패스워드 불일치로 실패")
  public void memberPasswordChange_fail_notMatchPassword() throws Exception {
    Member member = Member.builder().memberId(1L).password("qwer1234")
        .build();
    given(loadMemberPort.loadByMemberId(1L)).willReturn(member);
    given(passwordEncoder.matches(any(), any())).willReturn(false);

    assertThrows(NotMatchPasswordException.class,
        () -> passwordService.memberPasswordChange(passwordChangeCommand));
  }

  @Test
  @DisplayName("회원 패스워드 변경 성공")
  public void memberPasswordChange_success() throws Exception {
    Member member = Member.builder().memberId(1L).password("qwer1234")
        .build();
    given(loadMemberPort.loadByMemberId(1L)).willReturn(member);
    given(passwordEncoder.matches(any(), any())).willReturn(true);
    given(passwordEncoder.encode(any())).willReturn("changePassword");

    passwordService.memberPasswordChange(passwordChangeCommand);
    assertEquals(member.getPassword(), "changePassword");
  }

}