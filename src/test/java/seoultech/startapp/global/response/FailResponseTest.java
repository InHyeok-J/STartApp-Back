package seoultech.startapp.global.response;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import seoultech.startapp.global.exception.ErrorType;

class FailResponseTest {

  @Test
  @DisplayName("failResponse DTO 테스트")
  public void failResponse() throws Exception {
    String message = "badRequest";
    ErrorType errorType = ErrorType.INVALID_INPUT;
    FailResponse failResponse = new FailResponse(errorType.getStatusCode(), message, errorType.getErrorType());

    assertEquals( 400, failResponse.getStatus());
    assertEquals( message, failResponse.getMessage());
    assertEquals( "ST001", failResponse.getErrorCode());
  }
}