package seoultech.startapp.global.exception;

public class NotFoundResourceException extends BusinessException{

  public NotFoundResourceException( String message) {
    super(ErrorType.NOT_FOUND_RESOURCE, message);
  }
}
