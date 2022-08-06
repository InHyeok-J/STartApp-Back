package seoultech.startapp.event.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import seoultech.startapp.event.application.port.out.LoadEventPagingPort;
import seoultech.startapp.event.application.port.out.LoadEventPort;
import seoultech.startapp.event.application.port.out.RemoveEventPort;
import seoultech.startapp.event.application.port.out.SaveEventPort;
import seoultech.startapp.event.domain.Event;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventPersistenceAdapter implements LoadEventPort, SaveEventPort, RemoveEventPort, LoadEventPagingPort {
    private final JpaEventRepository jpaEventRepository;
    private final EventMapper eventMapper;
    @Override
    public Event loadEventById(Long eventId) {
        JpaEvent jpaEvent = jpaEventRepository.findById(eventId)
                                              .orElseThrow(() -> new NotFoundJpaEventException("id에 해당하는 이벤트가 없습니다."));
        return eventMapper.mapToDomainEvent(jpaEvent);
    }

    @Override
    public List<Event> loadAllEvent() {
        List<JpaEvent> jpaEvents = jpaEventRepository.findAllByOrderByStartTimeAsc();

        return eventMapper.mapToDomainEventList(jpaEvents);
    }

    @Override
    public void saveEvent(Event event) {
        JpaEvent jpaEvent = eventMapper.mapToJpaEvent(event);
        jpaEventRepository.save(jpaEvent);
    }

    @Override
    public Page<Event> loadAllEventByPaging(PageRequest pageRequest) {
        Page<JpaEvent> jpaEventPages = jpaEventRepository.findAllByOrderByStartTimeDesc(pageRequest);
        return eventMapper.mapToDomainEventPage(jpaEventPages);
    }

    @Override
    public void deleteById(Long eventId) {
        checkCanDelete(eventId);
        jpaEventRepository.deleteById(eventId);
    }

    private void checkCanDelete(Long eventId){
        jpaEventRepository.findById(eventId)
                          .orElseThrow(() -> new NotFoundJpaEventException("삭제하려고 하는 id에 해당하는 이벤트가 없습니다."));
    }
}
