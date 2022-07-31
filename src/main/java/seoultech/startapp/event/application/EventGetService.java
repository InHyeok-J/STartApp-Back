package seoultech.startapp.event.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.event.application.port.in.EventGetUseCase;
import seoultech.startapp.event.application.port.out.LoadEventPort;
import seoultech.startapp.event.domain.Event;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
class EventGetService implements EventGetUseCase {
    private final LoadEventPort loadEventPort;

    @Override
    @Transactional(readOnly = true)
    public EventResponse getEventOne(Long eventId) {
        Event event = loadEventPort.loadEventById(eventId);

        return EventResponse.eventToEventResponse(event);
    }



    @Override
    @Transactional(readOnly = true)
    public List<EventResponse> getAllEvent() {
        List<Event> events = loadEventPort.loadAllEvent();

        List<EventResponse> eventResponseList = new ArrayList<>();

        for(Event event : events){
            EventResponse eventResponse = EventResponse.eventToEventResponse(event);
            eventResponseList.add(eventResponse);
        }

        return eventResponseList;
    }
}
