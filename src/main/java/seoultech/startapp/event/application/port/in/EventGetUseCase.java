package seoultech.startapp.event.application.port.in;

import org.springframework.data.domain.PageRequest;
import seoultech.startapp.event.application.EventResponse;

import java.util.List;
import java.util.Map;

public interface EventGetUseCase {

    EventResponse getEventOne(Long eventId);

    List<EventResponse> getAllEvent();

    Map<String,Object> getAllEventByPaging(PageRequest pageRequest);
}
