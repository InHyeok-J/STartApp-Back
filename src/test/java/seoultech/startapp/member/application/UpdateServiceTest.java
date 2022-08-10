package seoultech.startapp.member.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import seoultech.startapp.member.application.port.in.command.UpdateMemberCommand;
import seoultech.startapp.member.application.port.out.LoadMemberPort;
import seoultech.startapp.member.application.port.out.SaveMemberPort;
import seoultech.startapp.member.domain.Member;
import seoultech.startapp.member.domain.MemberRole;
import seoultech.startapp.member.domain.StudentStatus;

@ExtendWith(MockitoExtension.class)
class UpdateServiceTest {
  @Mock
  LoadMemberPort loadMemberPort;

  @Mock
  SaveMemberPort saveMemberPort;

  @InjectMocks
  UpdateService updateService;

  UpdateMemberCommand updateMemberCommand;

  @BeforeEach
  void setUp(){
    updateMemberCommand = UpdateMemberCommand.builder()
        .memberId(1L)
        .name("홍길동")
        .phoneNo("010-0000-0000")
        .studentStatus("STUDENT")
        .department("학과")
        .memberShip(true)
        .email("email@email.com")
        .build();
  }

  @Test
  @DisplayName("업데이트 성공")
  public void updateSuccess() throws Exception {
    //given
    Member member = mockMember(updateMemberCommand.getMemberId());
    given(loadMemberPort.loadByMemberId(any())).willReturn(member);

    //when
    updateService.update(updateMemberCommand);
    //then
    assertEquals(updateMemberCommand.getName(), member.getName());
    verify(saveMemberPort, times(1)).save(member);
  }

  private Member mockMember(Long memberId){
    return Member.builder()
        .memberId(memberId)
        .studentNo("학번")
        .name("이름")
        .phoneNo("010-")
        .studentStatus(StudentStatus.STUDENT)
        .memberShip(true)
        .memberRole(MemberRole.MEMBER)
        .department("학과")
        .createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now())
        .build();
  }
}