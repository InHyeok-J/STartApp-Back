package seoultech.startapp.rent.adapter.in;

import lombok.Getter;
import lombok.NoArgsConstructor;
import seoultech.startapp.rent.application.port.in.command.RegisterRentItemCommand;

import java.util.List;

@Getter
@NoArgsConstructor
class RegisterRentItemRequest {

    private List<Long> itemIds;


    public RegisterRentItemCommand toRegisterCommand(Long rentId){
        return RegisterRentItemCommand.builder()
            .rentId(rentId)
            .itemIds(itemIds)
            .build();
    }
}
