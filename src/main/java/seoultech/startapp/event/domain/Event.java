package seoultech.startapp.event.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class Event {

    private Long eventId;

    private String title;

    private String formLink;

    private String imageUrl;

    private String color;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Builder
    public Event(Long eventId,
                 String title,
                 String formLink,
                 String imageUrl,
                 String color,
                 LocalDateTime startTime,
                 LocalDateTime endTime) {
        this.eventId = eventId;
        this.title = title;
        this.formLink = formLink;
        this.imageUrl = imageUrl;
        this.color = color;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
