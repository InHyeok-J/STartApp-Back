package seoultech.startapp.event.adapter.out;

import org.springframework.stereotype.Component;
import seoultech.startapp.event.domain.Event;

import java.util.List;
import java.util.stream.Collectors;

@Component
class EventMapper {

    Event mapToDomainEvent(JpaEvent jpaEvent){

        return Event.builder()
                    .eventId(jpaEvent.getId())
                    .color(jpaEvent.getColor())
                    .formLink(jpaEvent.getFormLink())
                    .imageUrl(jpaEvent.getImageUrl())
                    .endTime(jpaEvent.getEndTime())
                    .startTime(jpaEvent.getStartTime())
                    .title(jpaEvent.getTitle())
                    .build();
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
                        .color(event.getColor())
                        .imageUrl(event.getImageUrl())
                        .formLink(event.getFormLink())
                        .title(event.getTitle())
                        .startTime(event.getStartTime())
                        .endTime(event.getEndTime())
                        .build();
    }
}
