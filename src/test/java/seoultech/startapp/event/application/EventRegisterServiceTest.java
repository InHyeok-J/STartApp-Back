package seoultech.startapp.event.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import seoultech.startapp.event.application.port.in.EventCommand;

import java.time.LocalDateTime;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EventRegisterServiceTest {



    @Mock
    private EventRegisterService eventRegisterService;

    private EventCommand eventCommand;

    @BeforeEach
    void setUp(){
        eventCommand = EventCommand.builder()
                            .title("title")
                            .formLink("formLink")
                            .imageUrl("imagUrl")
                            .startTime(LocalDateTime.now())
                            .endTime(LocalDateTime.now().plusDays(3))
                            .build();

    }

    @Test
    @DisplayName("registerEvent가 1번 제대로 실행 되었는지 확인")
    void registerEvent_Ok(){
        eventRegisterService.registerEvent(eventCommand);

        verify(eventRegisterService,times(1)).registerEvent(eventCommand);
    }



}
