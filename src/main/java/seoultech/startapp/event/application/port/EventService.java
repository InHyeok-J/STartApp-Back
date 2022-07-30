package seoultech.startapp.event.application.port;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import seoultech.startapp.event.application.port.in.EventCommand;
import seoultech.startapp.event.application.port.in.EventGetUseCase;
import seoultech.startapp.event.application.port.in.EventPostUseCase;
import seoultech.startapp.event.application.port.out.LoadEventPort;
import seoultech.startapp.event.application.port.out.SaveEventPort;
import seoultech.startapp.event.domain.Event;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
class EventService implements EventGetUseCase, EventPostUseCase {

    private final LoadEventPort loadEventPort;
    private final SaveEventPort saveEventPort;
    @Override
    public EventResponse getEventOne(Long eventId) {
        Event event = loadEventPort.loadEventById(eventId);

        event.checkEventStatus();

        EventResponse eventResponse = EventResponse.builder()
            .eventId(event.getEventId())
            .formLink(event.getFormLink())
            .title(event.getTitle())
            .imageUrl(event.getImageUrl())
            .startTime(event.getStartTime())
            .endTime(event.getEndTime())
            .eventStatus(event.getEventStatus())
            .build();

        return eventResponse;
    }



    @Override
    public List<EventResponse> getAllEvent() {
        List<Event> events = loadEventPort.loadAllEvent();
        List<EventResponse> eventResponseList = new ArrayList<>();

        for(Event event : events){
            event.checkEventStatus();
            EventResponse eventResponse = EventResponse.builder()
                                                       .eventId(event.getEventId())
                                                       .formLink(event.getFormLink())
                                                       .title(event.getTitle())
                                                       .imageUrl(event.getImageUrl())
                                                       .startTime(event.getStartTime())
                                                       .endTime(event.getEndTime())
                                                       .eventStatus(event.getEventStatus())
                                                       .build();
            eventResponseList.add(eventResponse);
        }

        return eventResponseList;
    }

    @Override
    public void registerPost(EventCommand eventCommand) {

        Event event = Event.builder()
                           .title(eventCommand.getTitle())
                           .imageUrl(eventCommand.getImageUrl())
                           .formLink(eventCommand.getFormLink())
                           .startTime(eventCommand.getStartTime())
                           .endTime(eventCommand.getEndTime())
                           .build();

        saveEventPort.saveEvent(event);
    }

}
