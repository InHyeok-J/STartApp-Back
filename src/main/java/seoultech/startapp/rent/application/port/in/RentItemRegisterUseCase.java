package seoultech.startapp.rent.application.port.in;

import seoultech.startapp.rent.application.port.in.command.RegisterRentItemCommand;

public interface RentItemRegisterUseCase {

    void register(RegisterRentItemCommand registerRentItemCommand);
}
