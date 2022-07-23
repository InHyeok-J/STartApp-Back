package seoultech.startapp.event.adapter.in;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class EventResponse {

    private Long eventId;

    private String title;

    private String formLink;

    private String imageUrl;

    private String color;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private EventStatus eventStatus;

    @Builder
    public EventResponse(Long eventId,
                         String title,
                         String formLink,
                         String imageUrl,
                         String color,
                         LocalDateTime startTime,
                         LocalDateTime endTime,
                         EventStatus eventStatus) {
        this.eventId = eventId;
        this.title = title;
        this.formLink = formLink;
        this.imageUrl = imageUrl;
        this.color = color;
        this.startTime = startTime;
        this.endTime = endTime;
        this.eventStatus = eventStatus;
    }
}
