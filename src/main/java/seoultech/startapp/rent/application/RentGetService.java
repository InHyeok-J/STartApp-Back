package seoultech.startapp.rent.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import seoultech.startapp.rent.application.port.in.RentGetUseCase;
import seoultech.startapp.rent.application.port.in.command.RentPagingCommand;
import seoultech.startapp.rent.application.port.out.LoadRentPort;
import seoultech.startapp.rent.domain.RentStatus;

@Service
@RequiredArgsConstructor
class RentGetService implements RentGetUseCase {

    private final LoadRentPort loadRentPort;

    @Override
    public RentPagingResponse getByPaging(RentPagingCommand rentPagingCommand) {
        int page = rentPagingCommand.getPage();
        int count = rentPagingCommand.getCount();
        RentStatus rentStatus = rentPagingCommand.getRentStatus();

        Page<RentResponse> rentResponses = loadRentPort.loadByPaging(PageRequest.of(page, count), rentStatus)
                                                       .map(RentResponse::toListResponse);
        return new RentPagingResponse(rentResponses.getTotalPages(), rentResponses.getContent());
    }
}
