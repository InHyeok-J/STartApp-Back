package seoultech.startapp.rent.application.port.in.command;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import seoultech.startapp.global.common.SelfValidator;
import seoultech.startapp.rent.domain.RentStatus;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import java.util.HashSet;

@Getter
@NoArgsConstructor
public class UpdateRentStatusCommand extends SelfValidator<UpdateRentStatusCommand> {

    @NotNull
    private Long rentId;

    @NotNull
    private RentStatus rentStatus;


    @Builder
    public UpdateRentStatusCommand(Long rentId, String rentStatus) {
        this.rentId = rentId;
        this.rentStatus = rentStatusValidate(rentStatus);
        this.validateSelf();
    }

    private RentStatus rentStatusValidate(String rentStatus){
        try{
            return RentStatus.valueOf(rentStatus);
        }catch (Exception e){
            throw new ConstraintViolationException("잘못된 rentStatus를 입력했습니다.", new HashSet<>());
        }
    }
}
