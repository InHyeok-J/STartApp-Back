package seoultech.startapp.event.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EventRemoveServiceTest {

    @Mock
    private EventRemoveService eventRemoveService;

    @Test
    @DisplayName("삭제가 한 번 실행되는 지 확인")
    void removeEvent_ok(){

        eventRemoveService.removeEvent(any());
        verify(eventRemoveService,times(1)).removeEvent(any());
    }
}