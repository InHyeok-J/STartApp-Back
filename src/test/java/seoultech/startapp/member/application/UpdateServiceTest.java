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
import org.mockito.junit.jupiter.MockitoExtension;
import seoultech.startapp.helper.domain.MockDomainMember;
import seoultech.startapp.member.application.port.in.command.UpdateMemberCommand;
import seoultech.startapp.member.application.port.out.LoadMemberPort;
import seoultech.startapp.member.application.port.out.SaveMemberPort;
import seoultech.startapp.member.domain.Member;

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
        .department("학과")
        .memberShip(true)
        .build();
  }

  @Test
  @DisplayName("업데이트 성공")
  public void updateSuccess() throws Exception {
    //given
    Member member = MockDomainMember.generalMockMemberByMemberId(1L);
    given(loadMemberPort.loadByMemberId(any())).willReturn(member);

    //when
    updateService.update(updateMemberCommand);
    //then
    assertEquals(updateMemberCommand.getName(), member.getProfile().getName());
    verify(saveMemberPort, times(1)).save(member);
  }

}