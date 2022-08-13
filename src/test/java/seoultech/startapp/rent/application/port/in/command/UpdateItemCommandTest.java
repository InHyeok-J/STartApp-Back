package seoultech.startapp.rent.application.port.in.command;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class UpdateItemCommandTest {

    private UpdateItemCommand updateItemCommand;
    private final String FAIL_AVAILABLE = "TRUEE";

    @Test
    @DisplayName("updateItemCommand 생성 실패")
    void updateItemCommandToDomainItem_fail(){
        assertThrows(ConstraintViolationException.class,() -> UpdateItemCommand.builder()
                                                                         .available(FAIL_AVAILABLE)
                                                                         .build());
    }

}