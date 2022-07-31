package seoultech.startapp.global.exception;

public class InvalidHeaderException extends BusinessException{

  public InvalidHeaderException( String message) {
    super(ErrorType.INVALID_HEADER, message);
  }
}
