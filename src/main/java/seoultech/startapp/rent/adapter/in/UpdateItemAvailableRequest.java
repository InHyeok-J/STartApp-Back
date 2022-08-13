package seoultech.startapp.rent.adapter.in;

import lombok.Getter;
import seoultech.startapp.rent.application.port.in.command.UpdateItemAvailableCommand;

@Getter
class UpdateItemAvailableRequest {

    private Long itemId;
    private Boolean available;

    public UpdateItemAvailableCommand ToItemCommand(){
        return UpdateItemAvailableCommand.builder()
                                         .itemId(itemId)
                                         .available(this.available)
                                         .build();
    }
}
