package seoultech.startapp.rent.application.port.in;

import seoultech.startapp.rent.application.port.in.command.UpdateRentStatusCommand;

public interface RentUpdateUseCase {
    void updateByStatus(UpdateRentStatusCommand updateRentStatusCommand);
}
