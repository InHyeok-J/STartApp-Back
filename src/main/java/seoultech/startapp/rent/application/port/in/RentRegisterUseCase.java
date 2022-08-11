package seoultech.startapp.rent.application.port.in;

import seoultech.startapp.rent.application.port.in.command.RentCommand;

public interface RentRegisterUseCase {

    void registerRent(RentCommand rentCommand);
}
