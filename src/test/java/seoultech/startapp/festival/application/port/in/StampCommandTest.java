package seoultech.startapp.festival.application.port.in;

import static org.junit.jupiter.api.Assertions.*;

import javax.validation.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import seoultech.startapp.festival.domain.StampList;

class StampCommandTest {

  @Test
  @DisplayName("스탬프 validation 실패")
  public void stampValidation() throws Exception {
    String target = "exhibitions"; //잘못된 INPUT 값

    assertThrows(ConstraintViolationException.class, () -> StampCommand.builder()
        .memberId(1L)
        .target(target)
        .build());

  }

  @Test
  @DisplayName("스탬프 validation 성공")
  public void stampSuccess() throws Exception {
    String target = "ground"; // 맞는 값

    StampCommand command = StampCommand.builder()
        .memberId(1L)
        .target(target)
        .build();

    assertEquals(StampList.GROUND,command.getTarget());
  }
}