package seoultech.startapp.banner.application.port.in.command;

import static org.junit.jupiter.api.Assertions.*;

import javax.validation.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import seoultech.startapp.banner.domain.Banner;

class RegisterBannerCommandTest {

  @Test
  @DisplayName("command 생성시 validation 실패")
  public void validationFail() throws Exception {
    //given
    assertThrows(ConstraintViolationException.class, () -> RegisterBannerCommand.builder()
        .title("제목")
        .imageUrl("imageUrl")
        .priority(11)
        .build());
  }

  @Test
  @DisplayName("command로 Domain 생성 성공")
  public void commandtoDtoSuccesss() throws Exception {
    RegisterBannerCommand command = RegisterBannerCommand.builder()
        .title("제목")
        .imageUrl("imageUrl")
        .priority(1)
        .build();

    Banner banner = command.toDomainBanner();
    assertEquals(command.getImageUrl(), banner.getImageUrl());
    assertEquals(command.getPriority(), banner.getPriority());
    assertEquals(command.getTitle(), banner.getTitle());
  }
}