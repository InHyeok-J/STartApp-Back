package seoultech.startapp.event.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class EventTest {

    private static final LocalDateTime START_TIME = LocalDateTime.of(2022, 07, 18, 00, 00);
    private static final LocalDateTime END_TIME = LocalDateTime.of(2022,07,25,17,59);

    private static LocalDateTime CURRENT_BEFORE_TIME = LocalDateTime.of(2022,07,17,0,0);
    private static LocalDateTime CURRENT_END_TIME = LocalDateTime.of(2022,07,26,0,0);
    private static LocalDateTime CURRENT_PROCEEDING_TIME = LocalDateTime.of(2022,07,20,0,0);

    @Test
    @DisplayName("현재 시간이 이벤트의 시작보다 이른 시간일 때")
    void checkEventStatus_Before(){
        EventStatus eventStatus = checkEventStatus(CURRENT_BEFORE_TIME,START_TIME, END_TIME);

        assertThat(eventStatus).isEqualTo(EventStatus.BEFORE);
    }


    @Test
    @DisplayName("현재 시간이 이벤트의 끝보다 나중 시간일 때")
    void checkEventStatus_END(){
        EventStatus eventStatus = checkEventStatus(CURRENT_END_TIME,START_TIME, END_TIME);

        assertThat(eventStatus).isEqualTo(EventStatus.END);
    }

    @Test
    @DisplayName("현재 시간이 이벤트의 시작시간과 이벤트의 끝 시간의 사이 시간일 때")
    void checkEventStatus_PROCEEDING(){
        EventStatus eventStatus = checkEventStatus(CURRENT_PROCEEDING_TIME,START_TIME, END_TIME);

        assertThat(eventStatus).isEqualTo(EventStatus.PROCEEDING);
    }

    private EventStatus checkEventStatus(LocalDateTime currentTime,LocalDateTime startTime, LocalDateTime endTime){//현재 시각을 기준으로 평가

        if(currentTime.isBefore(startTime)){ //인자보다 과거일 때 true 리턴
            //현재 시각이 이벤트의 시작타임보다 전이면 시작 전
            return EventStatus.BEFORE;
        }
        else if(currentTime.isAfter(endTime)){
            //현재 시각이 이벤트의 끝 시작보다 후이면 이벤트가 끝남
            return EventStatus.END;
        }

        return EventStatus.PROCEEDING;
    }
}
