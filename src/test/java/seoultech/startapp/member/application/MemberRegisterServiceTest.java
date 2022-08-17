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
import seoultech.startapp.helper.domain.MockDomainMember;
import seoultech.startapp.member.application.port.in.command.RegisterCommand;
import seoultech.startapp.member.application.port.out.LoadMemberPort;
import seoultech.startapp.member.application.port.out.SaveMemberPort;
import seoultech.startapp.member.domain.Member;
import seoultech.startapp.member.exception.DuplicateStudentNoException;

@ExtendWith(MockitoExtension.class)
class MemberRegisterServiceTest {

  @Mock
  LoginService loginService;

  @Mock
  LoadMemberPort loadMemberPort;

  @Mock
  SaveMemberPort saveMemberPort;

  @Spy
  PasswordEncoder passwordEncoder;

  @InjectMocks
  RegisterService registerService;

  String studentNo = "180800";
  RegisterCommand registerCommand;
  Member savedMember;

  @BeforeEach
  void setUp() {
    this.registerCommand = new RegisterCommand(studentNo, "appPassword", "이름", "컴퓨터공학과",
        "010-9999-9999", "token..");

    this.savedMember = MockDomainMember.generalMockMemberByStudentNo(studentNo);
  }

  @Test
  @DisplayName("이미 가입된 학번으로 실패")
  public void duplicateStudentNo() {
    given(loadMemberPort.existByStudentNo(studentNo)).willReturn(true);
    assertThrows(DuplicateStudentNoException.class, ()-> registerService.register(registerCommand));
  }

  @Test
  @DisplayName("회원가입 성공")
  public void successRegister() throws Exception {
    given(loadMemberPort.existByStudentNo(studentNo)).willReturn(false);
    given(saveMemberPort.save(any())).willReturn(savedMember);

    AllToken tokens = registerService.register(registerCommand);

    verify(loginService,times(1)).generateToken(savedMember);
  }
}