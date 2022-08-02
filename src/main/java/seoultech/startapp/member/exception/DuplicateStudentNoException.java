package seoultech.startapp.member.exception;

import seoultech.startapp.global.exception.BusinessException;
import seoultech.startapp.global.exception.ErrorType;

public class DuplicateStudentNoException extends BusinessException {

  public DuplicateStudentNoException(String message) {
    super(ErrorType.DUPLICATE_STUDENT_NO, message);
  }
}
