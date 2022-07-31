package seoultech.startapp.event.adapter.out;

import org.springframework.stereotype.Component;
import seoultech.startapp.event.domain.Event;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
class EventMapper {

    Event mapToDomainEvent(JpaEvent jpaEvent){

        Event event = Event.builder()
                           .eventId(jpaEvent.getId())
                           .formLink(jpaEvent.getFormLink())
                           .imageUrl(jpaEvent.getImageUrl())
                           .endTime(jpaEvent.getEndTime())
                           .startTime(jpaEvent.getStartTime())
                           .title(jpaEvent.getTitle())
                           .build();
        event.checkEventStatus(LocalDateTime.now());
        return event;
    }

    List<Event> mapToDomainEventList(List<JpaEvent> jpaEvents){
        List<Event> events = jpaEvents.stream().map(jpaEvent ->
                                                         mapToDomainEvent(jpaEvent)
        ).collect(Collectors.toList());

        return events;
    }

    JpaEvent mapToJpaEvent(Event event){
        return JpaEvent.builder()
                        .id(event.getEventId() == null ? null : event.getEventId())
                        .imageUrl(event.getImageUrl())
                        .formLink(event.getFormLink())
                        .title(event.getTitle())
                        .startTime(event.getStartTime())
                        .endTime(event.getEndTime())
                        .build();
    }
}
