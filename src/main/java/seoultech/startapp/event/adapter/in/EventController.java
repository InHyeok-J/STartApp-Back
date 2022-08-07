package seoultech.startapp.event.adapter.in;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seoultech.startapp.event.application.EventResponse;
import seoultech.startapp.event.application.port.in.EventGetUseCase;
import seoultech.startapp.event.application.port.in.EventRegisterUseCase;
import seoultech.startapp.global.response.JsonResponse;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
class EventController {

    private final EventGetUseCase eventGetUseCase;
    private final EventRegisterUseCase eventRegisterUseCase;

    @GetMapping("{id}")
    public ResponseEntity<?> getEventById(@PathVariable("id") Long eventId){
        EventResponse eventResponse = eventGetUseCase.getEventOne(eventId);
        return JsonResponse.okWithData(HttpStatus.OK,"아이디에 해당하는 이벤트를 찾았습니다.",eventResponse);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllEvent(){
        List<EventResponse> allEvent = eventGetUseCase.getAllEvent();
        return JsonResponse.okWithData(HttpStatus.OK,"모든 이벤트를 찾았습니다.",allEvent);
    }

}
