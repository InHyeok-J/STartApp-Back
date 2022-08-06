package seoultech.startapp.event.application.port.in;

import org.springframework.data.domain.PageRequest;
import seoultech.startapp.event.application.EventPagingResult;
import seoultech.startapp.event.application.EventResponse;

import java.util.List;

public interface EventGetUseCase {

    EventResponse getEventOne(Long eventId);

    List<EventResponse> getAllEvent();

    EventPagingResult getAllEventByPaging(PageRequest pageRequest);
}
