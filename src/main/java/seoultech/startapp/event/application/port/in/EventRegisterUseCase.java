package seoultech.startapp.event.application.port.in;

import org.springframework.stereotype.Component;

@Component
public interface EventRegisterUseCase {

    void registerEvent(EventCommand eventCommand);
}
