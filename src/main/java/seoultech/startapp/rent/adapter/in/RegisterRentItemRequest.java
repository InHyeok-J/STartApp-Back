package seoultech.startapp.rent.adapter.in;

import lombok.Getter;
import lombok.NoArgsConstructor;
import seoultech.startapp.rent.application.port.in.command.RegisterRentItemCommand;

import java.util.List;

@Getter
@NoArgsConstructor
class RegisterRentItemRequest {

    private Long rentId;
    private String rentItemStatus;
    private List<Long> itemIds;


    public RegisterRentItemCommand toRegisterCommand(){
        return RegisterRentItemCommand.builder()
            .rentId(rentId)
            .rentItemStatus(rentItemStatus)
            .itemIds(itemIds)
            .build();
    }
}
