package seoultech.startapp.event.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.event.application.port.in.EventRemoveUseCase;
import seoultech.startapp.event.application.port.out.RemoveEventPort;


@Slf4j
@Service
@RequiredArgsConstructor
class EventRemoveService implements EventRemoveUseCase {

    private final RemoveEventPort removeEventPort;
    @Override
    @Transactional
    public void removeEvent(Long eventId) {
        removeEventPort.deleteById(eventId);
    }
}
