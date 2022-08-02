package seoultech.startapp.global.exception;

public class ExpiredJwtFailException extends BusinessException{

  public ExpiredJwtFailException(String message) {
    super(ErrorType.EXPIRED_JWT_FAIL, message);
  }
}
