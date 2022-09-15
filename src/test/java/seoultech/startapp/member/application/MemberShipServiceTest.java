package seoultech.startapp.member.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import seoultech.startapp.member.application.port.out.LoadMemberShipPort;
import seoultech.startapp.member.application.port.out.SaveMemberShipPort;
import seoultech.startapp.member.exception.AlreadyExistMemberShipException;

@ExtendWith(MockitoExtension.class)
class MemberShipServiceTest {

  @Mock
  LoadMemberShipPort loadMemberShipPort;
  @Mock
  SaveMemberShipPort saveMemberShipPort;

  @InjectMocks
  MemberShipService memberShipService;

  @Test
  @DisplayName("이미 자치회비 납부 명단에 있으면 에러")
  public void already_exist_membership() throws Exception {
    String alreadyStudentNo = "17101245";
    given(loadMemberShipPort.existByStudentNo(alreadyStudentNo)).willReturn(true);

    assertThrows(AlreadyExistMemberShipException.class, ()->
        memberShipService.addMemberShip(alreadyStudentNo));
  }

  @Test
  @DisplayName("자치회비 납부 명단 추가 성공")
  public void not_already_membership() throws Exception {
    String notAlreadyStudentNo = "17101245";
    given(loadMemberShipPort.existByStudentNo(notAlreadyStudentNo)).willReturn(false);

    memberShipService.addMemberShip(notAlreadyStudentNo);
    verify(saveMemberShipPort, times(1)).save(notAlreadyStudentNo);
  }
}