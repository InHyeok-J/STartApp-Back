package seoultech.startapp.event.application.port;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import seoultech.startapp.event.adapter.in.EventResponse;
import seoultech.startapp.event.adapter.in.EventStatus;
import seoultech.startapp.event.application.port.in.EventCommand;
import seoultech.startapp.event.application.port.in.EventUseCase;
import seoultech.startapp.event.application.port.out.LoadEventPort;
import seoultech.startapp.event.application.port.out.SaveEventPort;
import seoultech.startapp.event.domain.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
class EventService implements EventUseCase {

    private final LoadEventPort loadEventPort;
    private final SaveEventPort saveEventPort;
    @Override
    public EventResponse getEventOne(Long eventId) {
        Event event = loadEventPort.loadEventById(eventId);

        EventStatus eventStatus = checkEventStatus(event.getStartTime(),event.getEndTime());

        EventResponse result = getEventResponse(event, eventStatus);

        return result;
    }



    @Override
    public List<EventResponse> getAllEvent() {
        List<Event> events = loadEventPort.loadAllEvent();
        List<EventResponse> result = new ArrayList<>();

        for(Event event : events){
            EventStatus eventStatus = checkEventStatus(event.getStartTime(), event.getEndTime());
            EventResponse eventResponse = getEventResponse(event, eventStatus);
            result.add(eventResponse);
        }

        return result;
    }

    @Override
    public void registerPost(EventCommand eventCommand) {

        Event event = Event.builder()
                           .title(eventCommand.getTitle())
                           .color(eventCommand.getColor())
                           .imageUrl(eventCommand.getImageUrl())
                           .formLink(eventCommand.getFormLink())
                           .startTime(eventCommand.getStartTime())
                           .endTime(eventCommand.getEndTime())
                           .build();

        saveEventPort.saveEvent(event);
    }

    private EventStatus checkEventStatus(LocalDateTime startTime, LocalDateTime endTime){
        LocalDateTime currentTime = LocalDateTime.now(); //현재 시각을 기준으로 평가

        if(currentTime.isBefore(startTime)){ //인자보다 과거일 때 true 리턴
            //현재 시각이 이벤트의 시작타임보다 전이면 시작 전
            return EventStatus.BEFORE;
        }
        else if(currentTime.isAfter(endTime)){
            //현재 시각이 이벤트의 끝 시작보다 후이면 이벤트가 끝남
            return EventStatus.END;
        }

        return EventStatus.PROCEEDING;
    }

    private EventResponse getEventResponse(Event event, EventStatus eventStatus) {
        EventResponse result = EventResponse.builder()
                                            .eventId(event.getEventId())
                                            .color(event.getColor())
                                            .title(event.getTitle())
                                            .formLink(event.getFormLink())
                                            .imageUrl(event.getImageUrl())
                                            .startTime(event.getStartTime())
                                            .endTime(event.getEndTime())
                                            .eventStatus(eventStatus)
                                            .build();
        return result;
    }
}
