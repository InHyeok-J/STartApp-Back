package seoultech.startapp.rent.application.port.in;

import seoultech.startapp.rent.application.RentPagingResponse;
import seoultech.startapp.rent.application.port.in.command.RentPagingCommand;

public interface RentGetUseCase {

    RentPagingResponse getByPaging(RentPagingCommand rentPagingCommand);
}
