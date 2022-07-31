package seoultech.startapp.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import seoultech.startapp.global.response.JsonResponse;

import javax.validation.ConstraintViolationException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<?> handleBusinessException(BusinessException e) {
     ErrorType error = e.getErrorType();
    return JsonResponse.fail(error, e.getMessage());
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e) {
    return JsonResponse.fail(ErrorType.INVALID_INPUT, "잘못된 값입니다.");
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> internalServerException(Exception e){
    e.printStackTrace();
    return JsonResponse.fail(ErrorType.INTERNAL_SERVER_ERROR, "서버 에러");
  }

//  @ExceptionHandler(DateTimeFormatException.class)
//  public ResponseEntity<?> handleDateTimeFormatException(DateTimeFormatException e){
//    return JsonResponse.fail(HttpStatus.BAD_REQUEST,"StartTime or EndTime의 포맷이 잘못됐습니다. 'T'를 붙였나 확인해주세요");
//  }
}
