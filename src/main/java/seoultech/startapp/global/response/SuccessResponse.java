package seoultech.startapp.global.response;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class SuccessResponse {

  private final int status;
  private final String message;
  private List<Object> data = new ArrayList<>();

  public SuccessResponse(int status, String message) {
    this.status = status;
    this.message = message;
  }

  public SuccessResponse(int status, String message, Object data) {
    this.status = status;
    this.message = message;
    if(data instanceof List<?>){
      this.data = (List<Object>) data;
    }else {
      this.data.add(data);
    }
  }
}
