package seoultech.startapp.event.application.port.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import seoultech.startapp.event.domain.Event;

public interface LoadEventPagingPort {

    Page<Event> loadAllEventByPaging(PageRequest pageRequest);
}
