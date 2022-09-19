package seoultech.startapp.global.exception;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.util.ContentCachingRequestWrapper;
import seoultech.startapp.global.common.ErrorLoggerHelper;
import seoultech.startapp.global.common.ReusableRequestWrapper;
import seoultech.startapp.global.response.JsonResponse;

import javax.validation.ConstraintViolationException;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

  private final ErrorLoggerHelper errorLoggerHelper;

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<?> handleBusinessException(BusinessException e,
      ReusableRequestWrapper request)
      throws IOException {
    ErrorType error = e.getErrorType();
    errorLoggerHelper.log(request, error, e.getMessage());
    return JsonResponse.fail(error, e.getMessage());
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e,
      ReusableRequestWrapper request) throws IOException {
    errorLoggerHelper.log(request, ErrorType.INVALID_INPUT, e.getMessage());
    return JsonResponse.fail(ErrorType.INVALID_INPUT, "잘못된 값입니다.");
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> internalServerException(Exception e,
      ReusableRequestWrapper request) throws IOException {
    errorLoggerHelper.log(request, ErrorType.INTERNAL_SERVER_ERROR, e.getMessage());
    return JsonResponse.fail(ErrorType.INTERNAL_SERVER_ERROR, "서버 에러");
  }

  @ExceptionHandler(value = {HttpMessageNotReadableException.class,
      HttpRequestMethodNotSupportedException.class})
  public ResponseEntity<?> handleDateTimeFormatException(HttpMessageNotReadableException e,
      ReusableRequestWrapper request) throws IOException {
    errorLoggerHelper.log(request, ErrorType.INVALID_INPUT, e.getMessage());
    return JsonResponse.fail(ErrorType.INVALID_INPUT, "잘못된 요청입니다.");
  }
}
