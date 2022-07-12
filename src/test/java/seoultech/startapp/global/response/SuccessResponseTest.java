package seoultech.startapp.global.response;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class SuccessResponseTest {

  @Test
  @DisplayName("SuccessResponse DTO 생성자 테스트")
  public void successResponse() throws Exception {
    String message = "성공";
    SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(),message);

    assertEquals(response.getStatus(), 200);
    assertEquals(response.getMessage(), message);
    assertEquals(response.getData().size(), 0);
  }

  @Test
  @DisplayName("SuccessResponse DTO 생성자 테스트 데이터 추가")
  public void successResponseWithData() throws Exception {
    //given
    String message = "성공";
    class Temp {
      String value;
      Temp(String value ){
        this.value = value;
      }
      String getValue(){
        return value;
      }
    }
    Temp data = new Temp("값 테스트");

    //when
    SuccessResponse response = new SuccessResponse(HttpStatus.OK.value(),message, data);

    //then
    assertEquals(response.getStatus(), 200);
    assertEquals(response.getMessage(),message);
    assertEquals(response.getData().size(),1);
    assertEquals(response.getData().get(0).getClass(), Temp.class);
  }
}