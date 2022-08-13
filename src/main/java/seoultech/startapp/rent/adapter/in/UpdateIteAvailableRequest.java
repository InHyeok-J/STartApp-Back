package seoultech.startapp.rent.adapter.in;

import lombok.Getter;
import seoultech.startapp.rent.application.port.in.command.UpdateItemAvailableCommand;

@Getter
class UpdateIteAvailableRequest {

    private Boolean available;

    public UpdateItemAvailableCommand ToItemCommand(){
        return UpdateItemAvailableCommand.builder()
                                         .available(this.available)
                                         .build();
    }
}
