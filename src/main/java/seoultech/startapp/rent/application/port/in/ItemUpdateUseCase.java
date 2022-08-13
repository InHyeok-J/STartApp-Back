package seoultech.startapp.rent.application.port.in;

import seoultech.startapp.rent.application.port.in.command.UpdateItemCommand;

public interface ItemUpdateUseCase {

    void updateByAvailable(Long itemId, UpdateItemCommand updateItemCommand);
}
