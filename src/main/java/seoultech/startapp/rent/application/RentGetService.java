package seoultech.startapp.rent.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.rent.application.port.in.RentGetUseCase;
import seoultech.startapp.rent.application.port.in.command.RentCalendarCommand;
import seoultech.startapp.rent.application.port.in.command.RentPagingCommand;
import seoultech.startapp.rent.application.port.out.LoadRentItemPort;
import seoultech.startapp.rent.application.port.out.LoadRentPort;
import seoultech.startapp.rent.domain.Rent;
import seoultech.startapp.rent.domain.RentItem;
import seoultech.startapp.rent.domain.RentStatus;

@Service
@RequiredArgsConstructor
class RentGetService implements RentGetUseCase {

    private final LoadRentPort loadRentPort;
    private final LoadRentItemPort loadRentItemPort;
    @Transactional(readOnly = true)
    @Override
    public RentPagingResponse getByPaging(RentPagingCommand rentPagingCommand) {
        int page = rentPagingCommand.getPage();
        int count = rentPagingCommand.getCount();
        RentStatus rentStatus = rentPagingCommand.getRentStatus();

        Page<RentResponse> rentResponses = loadRentPort.loadByPaging(PageRequest.of(page, count), rentStatus)
                                                       .map(RentResponse::toListResponse);
        return new RentPagingResponse(rentResponses.getTotalPages(), rentResponses.getContent());
    }

    @Transactional(readOnly = true)
    @Override
    public RentResponse getDetail(Long rentId) {
        Rent rent = loadRentPort.loadByIdWithRenter(rentId);
        List<RentItem> rentItemList = loadRentItemPort.loadListByRent(rent);
        return RentResponse.toDetailResponse(rent,rentItemList);
    }

    @Override
    public List<RentResponse> getMyRentList(Long memberId) {
        List<Rent> rents = loadRentPort.loadListByMemberId(memberId);
        return rents.stream().map(RentResponse::toMyRentList).toList();
    }

    @Override
    public List<RentResponse> getCalendar(RentCalendarCommand command) {
        return null;
    }
}
