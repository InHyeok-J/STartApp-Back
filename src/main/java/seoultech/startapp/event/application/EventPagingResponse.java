package seoultech.startapp.event.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventPagingResponse {

    private int totalPage;

    private List<EventResponse> eventList;

}
