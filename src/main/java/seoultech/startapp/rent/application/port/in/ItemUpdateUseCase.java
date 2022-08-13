package seoultech.startapp.rent.application.port.in;

import seoultech.startapp.rent.application.port.in.command.UpdateItemAvailableCommand;

public interface ItemUpdateUseCase {

    void updateByAvailable(UpdateItemAvailableCommand updateItemAvailableCommand);
}
