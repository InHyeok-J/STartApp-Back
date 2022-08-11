package seoultech.startapp.rent.application.port.in;

import seoultech.startapp.rent.application.port.in.command.ItemCommand;

public interface ItemRegisterUseCase {

    void registerItem(ItemCommand itemCommand);
}
