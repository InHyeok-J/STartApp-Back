package seoultech.startapp.event.application.port.in;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventCommand {

    private String title;
    private String formLink;
    private String imageUrl;
    private String color;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Builder
    public EventCommand(String title, String formLink, String imageUrl, String color, LocalDateTime startTime, LocalDateTime endTime) {
        this.title = title;
        this.formLink = formLink;
        this.imageUrl = imageUrl;
        this.color = color;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
