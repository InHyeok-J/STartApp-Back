package seoultech.startapp.event.application.port.in;

import org.springframework.stereotype.Component;

@Component
public interface EventRemoveUseCase {
    void removeEvent(Long eventId);
}
