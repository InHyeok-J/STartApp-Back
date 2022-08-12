package seoultech.startapp.rent.adapter.out;

import seoultech.startapp.global.exception.BusinessException;
import seoultech.startapp.global.exception.ErrorType;

public class DuplicatedItemNo extends BusinessException {
    public DuplicatedItemNo(String message) {
        super(ErrorType.DUPLICATE_ITEM_NO, message);
    }
}
