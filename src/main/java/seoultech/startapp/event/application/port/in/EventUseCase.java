package seoultech.startapp.event.application.port.in;

import org.springframework.stereotype.Component;
import seoultech.startapp.event.adapter.in.EventResponse;

import java.util.List;

@Component
public interface EventUseCase {

    EventResponse getEventOne(Long eventId);

    List<EventResponse> getAllEvent();

    void registerPost(EventCommand eventCommand);

}
