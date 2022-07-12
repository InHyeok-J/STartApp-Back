package seoultech.startapp.global.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class JsonResponse {

  public static ResponseEntity<?> ok(HttpStatus status, String message){
    SuccessResponse response = new SuccessResponse(status.value(), message);
    return ResponseEntity.status(status.value())
        .body(response);
  }

  public static ResponseEntity<?> okWithData(HttpStatus status, String message, Object data){
    SuccessResponse response = new SuccessResponse(status.value() , message, data);
    return ResponseEntity.status(status.value())
        .body(response);
  }

  public static ResponseEntity<?> fail(HttpStatus status, String message){
    FailResponse response = new FailResponse(status.value(), message);
    return ResponseEntity.status(status.value())
        .body(response);
  }
}
