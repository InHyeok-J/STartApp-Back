package seoultech.startapp.global.exception;

import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import seoultech.startapp.global.response.JsonResponse;

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
}
