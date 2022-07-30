package seoultech.startapp.event.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class Event {

    private Long eventId;

    private String title;

    private String formLink;

    private String imageUrl;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private EventStatus eventStatus;


    @Builder
    public Event(Long eventId,
                 String title,
                 String formLink,
                 String imageUrl,
                 LocalDateTime startTime,
                 LocalDateTime endTime,
                 EventStatus eventStatus) {
        this.eventId = eventId;
        this.title = title;
        this.formLink = formLink;
        this.imageUrl = imageUrl;
        this.startTime = startTime;
        this.endTime = endTime;
        this.eventStatus = eventStatus;
    }

    public void checkEventStatus(){
        LocalDateTime currentTime = LocalDateTime.now(); //현재 시각을 기준으로 평가

        if(currentTime.isBefore(this.startTime)){ //인자보다 과거일 때 true 리턴
            //현재 시각이 이벤트의 시작타임보다 전이면 시작 전
            this.eventStatus = EventStatus.BEFORE;
            return;
        }
        else if(currentTime.isAfter(this.endTime)){
            //현재 시각이 이벤트의 끝 시작보다 후이면 이벤트가 끝남
            this.eventStatus = EventStatus.END;
            return;
        }
        //현재시각이 이벤트 시작 시각보다 후 끝 시간보다 전임
        this.eventStatus = EventStatus.PROCEEDING;
    }
}
