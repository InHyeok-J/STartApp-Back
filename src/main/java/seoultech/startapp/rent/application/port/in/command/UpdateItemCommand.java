package seoultech.startapp.rent.application.port.in.command;

import lombok.Builder;
import lombok.Getter;
import seoultech.startapp.global.common.SelfValidator;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import java.util.HashSet;

@Getter
public class UpdateItemCommand extends SelfValidator<UpdateItemCommand> {

    @NotNull
    private Boolean available;

    @Builder
    public UpdateItemCommand(String available) {
        this.available = validateAvailable(available);
        this.validateSelf();
    }

    private Boolean validateAvailable(String available){
        if(available.equalsIgnoreCase("true")){
            return Boolean.TRUE;
        }else if(available.equalsIgnoreCase("false")){
            return Boolean.FALSE;
        }else{
            throw new ConstraintViolationException("잘못된 값을 입력했습니다.",new HashSet<>());
        }
    }
}
