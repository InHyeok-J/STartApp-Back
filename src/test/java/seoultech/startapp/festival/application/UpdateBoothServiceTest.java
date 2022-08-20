package seoultech.startapp.festival.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import seoultech.startapp.festival.application.port.in.command.UpdateCongestionBoothCommand;
import seoultech.startapp.festival.application.port.out.LoadBoothPort;
import seoultech.startapp.festival.application.port.out.SaveBoothPort;
import seoultech.startapp.festival.domain.Booth;

@ExtendWith(MockitoExtension.class)
class UpdateBoothServiceTest {

  @Mock
  LoadBoothPort loadBoothPort;

  @Mock
  SaveBoothPort saveBoothPort;

  @InjectMocks
  UpdateBoothService updateBoothService;

  UpdateCongestionBoothCommand command;
  Booth savedBooth;
  @BeforeEach
  void setUp(){
    command = new UpdateCongestionBoothCommand(3, 1L);
    savedBooth = Booth.builder()
        .boothId(command.getBoothId())
        .name("부스이름")
        .congestion(1)
        .build();
  }


  @Test
  @DisplayName("혼잡도 변환 성공")
  public void congestionUpdateSuccess() throws Exception {
    given(loadBoothPort.loadByBoothId(command.getBoothId())).willReturn(savedBooth);
    updateBoothService.update(command);

    assertEquals(savedBooth.getCongestion(), command.getCongestion());
  }
}