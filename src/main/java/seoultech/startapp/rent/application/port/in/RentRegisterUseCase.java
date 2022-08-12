package seoultech.startapp.rent.application.port.in;

import seoultech.startapp.rent.application.port.in.command.RegisterRentCommand;

public interface RentRegisterUseCase {

    void registerRent(RegisterRentCommand registerRentCommand);
}
