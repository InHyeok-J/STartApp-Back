package seoultech.startapp.rent.application;

import seoultech.startapp.global.exception.BusinessException;
import seoultech.startapp.global.exception.ErrorType;

public class NotMatchMemberAccountException extends BusinessException {
    public NotMatchMemberAccountException(String message) {
        super(ErrorType.NOT_MATCH_MEMBER_ACCOUNT,message);
    }
}
