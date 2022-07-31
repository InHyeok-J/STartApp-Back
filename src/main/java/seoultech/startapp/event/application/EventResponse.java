package seoultech.startapp.event.application;

import lombok.Builder;
import lombok.Getter;
import seoultech.startapp.event.domain.Event;
import seoultech.startapp.event.domain.EventStatus;

import java.time.LocalDateTime;

@Getter
public class EventResponse {

    private Long eventId;

    private String title;

    private String formLink;

    private String imageUrl;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private EventStatus eventStatus;

    @Builder
    private EventResponse(Long eventId,
                         String title,
                         String formLink,
                         String imageUrl,
                         LocalDateTime startTime,
                         LocalDateTime endTime,
                         EventStatus eventStatus) {
        this.eventId = eventId;
        this.title = title;
        this.formLink = formLink;
        this.imageUrl = imageUrl;
        this.startTime = startTime;
        this.endTime = endTime;
        this.eventStatus = eventStatus;
    }

    public static EventResponse eventToEventResponse(Event event) {
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

}
