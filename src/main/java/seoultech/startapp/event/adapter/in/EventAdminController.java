package seoultech.startapp.event.adapter.in;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import seoultech.startapp.event.application.EventPagingResponse;
import seoultech.startapp.event.application.port.in.EventCommand;
import seoultech.startapp.event.application.port.in.EventGetUseCase;
import seoultech.startapp.event.application.port.in.EventRegisterUseCase;
import seoultech.startapp.event.application.port.in.EventRemoveUseCase;
import seoultech.startapp.global.response.JsonResponse;

@Slf4j
@RestController
@RequestMapping("/api/admin/event")
@RequiredArgsConstructor
class EventAdminController {

    private final EventRegisterUseCase eventRegisterUseCase;
    private final EventRemoveUseCase eventRemoveUseCase;
    private final EventGetUseCase eventGetUseCase;

    @GetMapping("/list")
    public ResponseEntity<?> getAllEventByPaging(@RequestParam int page, @RequestParam(defaultValue = "10",required = false) int count){


        EventPagingResponse pageEvents = eventGetUseCase.getAllEventByPaging(page, count);

        return JsonResponse.okWithData(HttpStatus.OK,"페이지에 해당하는 이벤트를 찾았습니다.",pageEvents);
    }

    @PostMapping("")
    public ResponseEntity<?> registerEvent(@RequestBody EventRequest eventRequest){
        EventCommand eventCommand = eventRequest.ToEventCommand();
        eventRegisterUseCase.registerEvent(eventCommand);
        return JsonResponse.ok(HttpStatus.CREATED, "이벤트를 생성했습니다.");
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<?> removeEvent(@PathVariable("eventId") Long eventId){
        eventRemoveUseCase.removeEvent(eventId);
        return JsonResponse.ok(HttpStatus.OK,"이벤트를 삭제했습니다.");
    }

}
