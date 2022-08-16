package seoultech.startapp.rent.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import seoultech.startapp.rent.application.port.in.RentGetUseCase;
import seoultech.startapp.rent.application.port.out.LoadRentPort;
import seoultech.startapp.rent.domain.RentStatus;

@Service
@RequiredArgsConstructor
class RentGetService implements RentGetUseCase {

    private final LoadRentPort loadRentPort;

    @Override
    public RentPagingResponse getByPaging(int page, int count, String status) {
        Page<RentResponse> rentResponses = loadRentPort.loadByPaging(PageRequest.of(page, count), RentStatus.valueOf(status))
                                                       .map(RentResponse::rentToRentResponse);
        return new RentPagingResponse(rentResponses.getTotalPages(), rentResponses.getContent());
    }
}
