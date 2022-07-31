package seoultech.startapp.event.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import seoultech.startapp.event.application.port.out.LoadEventPort;
import seoultech.startapp.event.domain.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventGetServiceTest {

    @Mock
    private LoadEventPort loadEventPort;

    @InjectMocks
    private EventGetService eventGetService;

    private Event eventOne;
    private List<Event> eventList = new ArrayList<>();
    @BeforeEach
    void setUp(){
        eventOne = Event.builder()
                              .eventId(1L)
                              .title("title")
                              .formLink("formLink")
                              .imageUrl("imagUrl")
                              .startTime(LocalDateTime.now())
                              .endTime(LocalDateTime.now().plusDays(3))
                              .build();

        for(int i = 2;i<10;i++){
            Event event = Event.builder()
                               .eventId(Long.valueOf(i))
                               .title("title" + i)
                               .formLink("formLink" + i)
                               .imageUrl("imagUrl" + i)
                               .startTime(LocalDateTime.now())
                               .endTime(LocalDateTime.now().plusDays(3))
                               .build();

            eventList.add(event);
        }
    }

    @Test
    @DisplayName("한 개 값을 잘 가져오는 지 확인")
    void getEventOne_Ok(){



        when(loadEventPort.loadEventById(1L)).thenReturn(eventOne);

        EventResponse event = eventGetService.getEventOne(1L);

        assertThat(event.getEventId()).isEqualTo(eventOne.getEventId());
    }

    @Test
    @DisplayName("모든 값을 잘 가져오는 지 확인")
    void getEventAll_Ok(){


        when(loadEventPort.loadAllEvent()).thenReturn(eventList);

        List<EventResponse> allEvent = eventGetService.getAllEvent();

        assertThat(allEvent.size()).isEqualTo(8);
    }

}