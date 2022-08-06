package seoultech.startapp.event.application.port.out;

import seoultech.startapp.event.domain.Event;

import java.util.List;

public interface LoadEventPort {

    Event loadEventById(Long eventId);

    List<Event> loadAllEvent();


}
