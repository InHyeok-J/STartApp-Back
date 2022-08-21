package seoultech.startapp.rent.application.port.in.command;

import lombok.Builder;
import lombok.Getter;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;

@Getter
public class RegisterRentItemCommand {

    @NotNull
    private Long rentId;

    @NotNull
    private List<Long> itemIds;

    @Builder
    public RegisterRentItemCommand(Long rentId, List<Long> itemIds) {
        this.rentId = rentId;
        this.itemIds = itemIds;
    }

}
