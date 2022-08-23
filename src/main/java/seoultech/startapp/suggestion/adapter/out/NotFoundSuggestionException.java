package seoultech.startapp.suggestion.adapter.out;

import seoultech.startapp.global.exception.BusinessException;
import seoultech.startapp.global.exception.ErrorType;

public class NotFoundSuggestionException extends BusinessException {

  public NotFoundSuggestionException(String message) {
    super(ErrorType.NOT_FOUND_RESOURCE, message);
  }
}
