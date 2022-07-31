package seoultech.startapp.event.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import seoultech.startapp.event.application.port.in.EventCommand;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class Event {

    private Long eventId;

    private String title;

    private String formLink;

    private String imageUrl;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private EventStatus eventStatus;


    @Builder
    public Event(Long eventId,
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

    public void checkEventStatus(LocalDateTime currentTime){

        if(currentTime.isBefore(this.startTime)){
            this.eventStatus = EventStatus.BEFORE;
            return;
        }
        else if(currentTime.isAfter(this.endTime)){
            this.eventStatus = EventStatus.END;
            return;
        }

        this.eventStatus = EventStatus.PROCEEDING;
    }

    public static Event eventCommandToEvent(EventCommand eventCommand){
        Event event = Event.builder()
                           .title(eventCommand.getTitle())
                           .imageUrl(eventCommand.getImageUrl())
                           .formLink(eventCommand.getFormLink())
                           .startTime(eventCommand.getStartTime())
                           .endTime(eventCommand.getEndTime())
                           .build();
        return event;
    }
}
