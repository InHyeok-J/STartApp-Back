package seoultech.startapp.event.application.port.out;

import seoultech.startapp.event.domain.Event;

public interface SaveEventPort {

    void saveEvent(Event event);
}
