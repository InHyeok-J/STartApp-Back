package seoultech.startapp.event.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.event.application.port.in.EventCommand;
import seoultech.startapp.event.application.port.in.EventRegisterUseCase;
import seoultech.startapp.event.application.port.out.SaveEventPort;
import seoultech.startapp.event.domain.Event;

@Slf4j
@Service
@RequiredArgsConstructor
class EventRegisterService implements EventRegisterUseCase {
    private final SaveEventPort saveEventPort;

    @Transactional
    @Override
    public void registerEvent(EventCommand eventCommand) {

        Event event = eventCommand.ToDomainEvent();

        saveEventPort.saveEvent(event);
    }
}
