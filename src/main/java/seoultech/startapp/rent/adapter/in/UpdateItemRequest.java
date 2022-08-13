package seoultech.startapp.rent.adapter.in;

import lombok.Getter;
import seoultech.startapp.rent.application.port.in.command.UpdateItemCommand;

@Getter
class UpdateItemRequest {

    private String available;

    public UpdateItemCommand ToItemCommand(){
        return UpdateItemCommand.builder()
            .available(this.available)
            .build();
    }
}
