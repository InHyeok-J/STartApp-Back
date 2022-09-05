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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import seoultech.startapp.global.common.S3Uploader;
import seoultech.startapp.helper.domain.MockDomainMember;
import seoultech.startapp.member.application.port.in.command.RegisterCommand;
import seoultech.startapp.member.application.port.out.DeleteMemberPort;
import seoultech.startapp.member.application.port.out.LoadMemberPort;
import seoultech.startapp.member.application.port.out.LoadMemberShipPort;
import seoultech.startapp.member.application.port.out.RedisCachePort;
import seoultech.startapp.member.application.port.out.SaveMemberPort;
import seoultech.startapp.member.application.port.out.SlackSenderPort;
import seoultech.startapp.member.domain.Member;
import seoultech.startapp.member.domain.MemberProfile;
import seoultech.startapp.member.domain.MemberStatus;
import seoultech.startapp.member.exception.DuplicateStudentNoException;
import seoultech.startapp.member.exception.LeaveMemberException;
import seoultech.startapp.member.exception.NotMatchPhoneAuthException;

@ExtendWith(MockitoExtension.class)
class MemberRegisterServiceTest {

  @Mock
  DeleteMemberPort deleteMemberPort;

  @Mock
  S3Uploader s3Uploader;

  @Mock
  LoadMemberPort loadMemberPort;

  @Mock
  SaveMemberPort saveMemberPort;

  @Spy
  PasswordEncoder passwordEncoder;

  @Mock
  SlackSenderPort slackSenderPort;
  @Mock
  RedisCachePort redisCachePort;

  @Mock
  LoadMemberShipPort loadMemberShipPort;
  @InjectMocks
  RegisterService registerService;

  String studentNo = "180800";
  RegisterCommand registerCommand;

  @BeforeEach
  void setUp() {
    this.registerCommand = RegisterCommand.builder()
        .name("조인혁")
        .department("컴퓨터공학과")
        .studentNo(studentNo)
        .appPassword("qwer1234")
        .fcmToken("fcmToken..")
        .file(new MockMultipartFile("data", "filename.txt", "text/plain", "some xml".getBytes()))
        .phoneNo("010-2642-2713")
        .build();
  }

  @Test
  @DisplayName("휴대폰인증이 안된 회원가입 요청 실패")
  public void signUp_fail_notauthPhone() throws Exception {
    given(redisCachePort.findByKey("PHONE-"+registerCommand.getPhoneNo())).willReturn(null);

    assertThrows(NotMatchPhoneAuthException.class, ()-> registerService.register(registerCommand));
  }
  @Test
  @DisplayName("학생증 인증 진행중인 학번으로 회원가입 시 실패")
  public void duplicateStudentNoByPreCardAuth() {
    Member preCardAuthMember = Member.builder().memberProfile(
        MemberProfile.builder().studentNo(studentNo).build()
        )
        .memberStatus(MemberStatus.PRE_CARD_AUTH).build();
    given(redisCachePort.findByKey("PHONE-"+registerCommand.getPhoneNo())).willReturn("any");
    given(loadMemberPort.loadByStudentNoNullable(studentNo)).willReturn(preCardAuthMember);
    assertThrows(DuplicateStudentNoException.class,
        () -> registerService.register(registerCommand));
  }

  @Test
  @DisplayName("학생증 인증 후 학생, 회원가입시 중복 실패")
  public void duplicateStudentNoByPostCardAuth() throws Exception {
    Member preCardAuthMember = Member.builder().memberProfile(
            MemberProfile.builder().studentNo(studentNo).build()
        )
        .memberStatus(MemberStatus.POST_CARD_AUTH).build();
    given(redisCachePort.findByKey("PHONE-"+registerCommand.getPhoneNo())).willReturn("any");
    given(loadMemberPort.loadByStudentNoNullable(studentNo)).willReturn(preCardAuthMember);
    assertThrows(DuplicateStudentNoException.class,
        () -> registerService.register(registerCommand));
  }


  @Test
  @DisplayName("회원가입 성공 후 Slack 알림 전송")
  public void signUpSuccess() throws Exception {
    String imageUrl = "image";
    Member jpaSavedMember = MockDomainMember.generalMockMemberByMemberId(1L);

    given(redisCachePort.findByKey("PHONE-"+registerCommand.getPhoneNo())).willReturn("any");
    given(loadMemberPort.loadByStudentNoNullable(studentNo)).willReturn(null);
    given(s3Uploader.uploadFile(any(), any())).willReturn(imageUrl);
    given(saveMemberPort.save(any())).willReturn(jpaSavedMember);

    registerService.register(registerCommand);

    verify(slackSenderPort, times(1)).sendStudentCard(any());
  }


}