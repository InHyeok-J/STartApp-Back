package seoultech.startapp.event.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


class EventTest {

    private Event beforeEvent;
    private Event proceedingEvent;
    private Event endEvent;
    
    private LocalDateTime currentTime;


    @BeforeEach
    void setEvent(){

        currentTime = LocalDateTime.now();

        beforeEvent = Event.builder()
                        .eventId(1L)
                        .imageUrl("imageUrl1")
                        .formLink("formLink1")
                        .startTime(currentTime.plusDays(1))
                        .endTime(currentTime.plusDays(3))
                        .build();

        proceedingEvent = Event.builder()
                           .eventId(2L)
                           .imageUrl("imageUrl2")
                           .formLink("formLink2")
                           .startTime(currentTime.minusDays(1))
                           .endTime(currentTime.plusDays(3))
                           .build();

        endEvent = Event.builder()
                           .eventId(3L)
                           .imageUrl("imageUrl3")
                           .formLink("formLink3")
                           .startTime(currentTime.minusDays(5))
                           .endTime(currentTime.minusDays(3))
                           .build();
    }

    @Test
    @DisplayName("이벤트가 시작 전 일 때")
    void beforeEvent_ok(){
        beforeEvent.checkEventStatus(currentTime);

        assertThat(beforeEvent.getEventStatus()).isEqualTo(EventStatus.BEFORE);
    }
    @Test
    @DisplayName("이벤트가 시작 중 일 때")
    void proceedingEvent_ok(){
        proceedingEvent.checkEventStatus(currentTime);

        assertThat(proceedingEvent.getEventStatus()).isEqualTo(EventStatus.PROCEEDING);

    }

    @Test
    @DisplayName("이벤트가 끝났을 때")
    void endEvent_ok(){
        endEvent.checkEventStatus(currentTime);
        assertThat(endEvent.getEventStatus()).isEqualTo(EventStatus.END);
    }

}
