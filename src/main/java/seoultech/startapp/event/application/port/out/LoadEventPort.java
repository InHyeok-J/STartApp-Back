package seoultech.startapp.event.application.port.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import seoultech.startapp.event.domain.Event;

import java.util.List;

public interface LoadEventPort {

    Event loadEventById(Long eventId);

    List<Event> loadAllEvent();

    Page<Event> loadAllEventByPaging(PageRequest pageRequest);


}
