package seoultech.startapp.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import seoultech.startapp.global.response.JsonResponse;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<?> handleBusinessException(BusinessException e) {
    return JsonResponse.fail(e.getStatus(), e.getMessage());
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e) {
    return JsonResponse.fail(HttpStatus.BAD_REQUEST, "잘못된 값입니다.");
  }

//  @ExceptionHandler(DateTimeFormatException.class)
//  public ResponseEntity<?> handleDateTimeFormatException(DateTimeFormatException e){
//    return JsonResponse.fail(HttpStatus.BAD_REQUEST,"StartTime or EndTime의 포맷이 잘못됐습니다. 'T'를 붙였나 확인해주세요");
//  }
}
