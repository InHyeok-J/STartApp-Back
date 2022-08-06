package seoultech.startapp.event.adapter.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import seoultech.startapp.event.application.port.out.LoadEventPagingPort;
import seoultech.startapp.event.application.port.out.RemoveEventPort;
import seoultech.startapp.event.domain.Event;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventAdminPersistenceAdapter implements RemoveEventPort, LoadEventPagingPort {

    private final JpaEventRepository jpaEventRepository;
    private final EventMapper eventMapper;


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

    public void checkCanDelete(Long eventId){
        jpaEventRepository.findById(eventId)
                          .orElseThrow(() -> new NotFoundJpaEventException("삭제하려고 하는 id에 해당하는 이벤트가 없습니다."));
    }
}
