package seoultech.startapp.event.adapter.in;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
class EventRequest {

    private final String title;
    private final String formLink;
    private final String imageUrl;
    private final String color;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

}
