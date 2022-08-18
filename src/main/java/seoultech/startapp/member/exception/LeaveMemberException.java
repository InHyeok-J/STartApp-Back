package seoultech.startapp.member.exception;

import seoultech.startapp.global.exception.BusinessException;
import seoultech.startapp.global.exception.ErrorType;

public class LeaveMemberException extends BusinessException {

  public LeaveMemberException(String message) {
    super(ErrorType.LEAVE_MEMBER, message);
  }
}
