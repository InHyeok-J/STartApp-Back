package seoultech.startapp.rent.application.port.in.command;

import lombok.Builder;
import lombok.Getter;
import seoultech.startapp.rent.domain.RentStatus;

import javax.validation.ConstraintViolationException;
import java.util.HashSet;

@Getter
public class RentPagingCommand {

    private int page;
    private int count;
    private RentStatus rentStatus;

    @Builder
    public RentPagingCommand(int page, int count, String rentStatus) {
        this.page = page;
        this.count = count;
        this.rentStatus = validate(rentStatus);
    }
    private RentStatus validate(String rentStatus){
        try{
            return RentStatus.valueOf(rentStatus);
        }catch (Exception e){
            throw new ConstraintViolationException("validation fail", new HashSet<>());
        }
    }
}
