package seoultech.startapp.event.application.port.in;

import seoultech.startapp.event.application.EventPagingResponse;
import seoultech.startapp.event.application.EventResponse;

import java.util.List;

public interface EventGetUseCase {

    EventResponse getEventOne(Long eventId);

    List<EventResponse> getAllEvent();

    EventPagingResponse getAllEventByPaging(int page, int count);
}
