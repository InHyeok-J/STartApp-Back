package seoultech.startapp.rent.application.port.in.command;

import lombok.Builder;
import lombok.Getter;
import seoultech.startapp.rent.domain.RentItemStatus;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;

@Getter
public class RegisterRentItemCommand {

    @NotNull
    private Long rentId;

    @NotNull
    private RentItemStatus rentItemStatus;

    @NotNull
    private List<Long> itemIds;

    @Builder
    public RegisterRentItemCommand(Long rentId, String rentItemStatus, List<Long> itemIds) {
        this.rentId = rentId;
        this.rentItemStatus = rentItemStatusValidate(rentItemStatus);
        this.itemIds = itemIds;
    }

    private RentItemStatus rentItemStatusValidate(String rentItemStatus){
        try{
            return RentItemStatus.valueOf(rentItemStatus);
        }catch (Exception e){
            throw new ConstraintViolationException("잘못된 rentItemStatus를 입력했습니다.", new HashSet<>());
        }
    }
}
