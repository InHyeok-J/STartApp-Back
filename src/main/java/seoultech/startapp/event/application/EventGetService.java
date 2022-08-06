package seoultech.startapp.event.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.event.application.port.in.EventGetUseCase;
import seoultech.startapp.event.application.port.out.LoadEventPagingPort;
import seoultech.startapp.event.application.port.out.LoadEventPort;
import seoultech.startapp.event.domain.Event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
class EventGetService implements EventGetUseCase {
    private final LoadEventPort loadEventPort;
    private final LoadEventPagingPort loadEventPagingPort;

    @Override
    @Transactional(readOnly = true)
    public EventResponse getEventOne(Long eventId) {
        Event event = loadEventPort.loadEventById(eventId);

        return EventResponse.eventToEventResponse(event);
    }



    @Override
    @Transactional(readOnly = true)
    public List<EventResponse> getAllEvent() {
        return loadEventPort.loadAllEvent().stream()
                            .map(event -> EventResponse.eventToEventResponse(event))
                            .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String,Object> getAllEventByPaging(PageRequest pageRequest) {

        Page<EventResponse> eventResponses = loadEventPagingPort.loadAllEventByPaging(pageRequest)
                                               .map(EventResponse::eventToEventResponse);

        Map<String,Object> pageResult = new HashMap<>();

        pageResult.put("totalPage",eventResponses.getTotalPages());
        pageResult.put("eventList",eventResponses.getContent());

        return pageResult;
    }
}
