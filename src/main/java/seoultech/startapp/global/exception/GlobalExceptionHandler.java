package seoultech.startapp.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import seoultech.startapp.global.response.JsonResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<?> handleBusinessException(BusinessException e){
    return JsonResponse.fail(e.getStatus(), e.getMessage());
  }
}
