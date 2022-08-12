package seoultech.startapp.rent.application.port.in;

import seoultech.startapp.rent.application.port.in.command.RegisterItemCommand;

public interface ItemRegisterUseCase {

    void registerItem(RegisterItemCommand registerItemCommand);
}
