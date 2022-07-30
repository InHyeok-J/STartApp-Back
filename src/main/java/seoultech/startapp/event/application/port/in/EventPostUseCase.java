package seoultech.startapp.event.application.port.in;

import org.springframework.stereotype.Component;

@Component
public interface EventPostUseCase {

    void registerPost(EventCommand eventCommand);
}
