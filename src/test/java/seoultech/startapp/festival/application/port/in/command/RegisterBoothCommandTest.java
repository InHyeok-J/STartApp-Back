package seoultech.startapp.festival.application.port.in.command;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import seoultech.startapp.festival.domain.Booth;

class RegisterBoothCommandTest {

  @Test
  @DisplayName("Booth 도메인 엔티티 변환 성공")
  public void commandToBoothEntity() throws Exception {
    RegisterBoothCommand command = new RegisterBoothCommand("이름");
    Booth booth = command.toDomainBooth();

    assertEquals(booth.getName(), command.getName());
    assertEquals(booth.getCongestion(),1);
  }
}