package seoultech.startapp.rent.application.port.in;

import seoultech.startapp.rent.application.RentPagingResponse;
import seoultech.startapp.rent.application.RentResponse;
import seoultech.startapp.rent.application.port.in.command.RentCalendarCommand;
import seoultech.startapp.rent.application.port.in.command.RentPagingCommand;

import java.util.List;

public interface RentGetUseCase {

    RentPagingResponse getByPaging(RentPagingCommand rentPagingCommand);
    RentResponse getDetail(Long rentId);
    List<RentResponse> getMyRentList(Long memberId);
    List<RentResponse> getCalendar(RentCalendarCommand rentCalendarCommand);
}
