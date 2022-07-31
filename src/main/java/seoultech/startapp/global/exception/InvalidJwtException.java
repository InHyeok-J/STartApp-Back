package seoultech.startapp.global.exception;

public class InvalidJwtException extends BusinessException{

  public InvalidJwtException( String message) {
    super(ErrorType.INVALID_JWT_FAIl, message);
  }
}
