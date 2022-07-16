package seoultech.startapp.global.response;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class FailResponseTest {

  @Test
  @DisplayName("failResponse DTO 테스트")
  public void failResponse() throws Exception {
    String message = "badRequest";
    FailResponse failResponse = new FailResponse(HttpStatus.BAD_REQUEST.value(), message);

    assertEquals(failResponse.getStatus(), 400);
    assertEquals(failResponse.getMessage(), message);
  }
}