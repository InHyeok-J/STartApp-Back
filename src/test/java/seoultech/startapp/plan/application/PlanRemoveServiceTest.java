package seoultech.startapp.plan.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PlanRemoveServiceTest {

    @Mock
    private PlanRemoveService planRemoveService;

    @Test
    @DisplayName("삭제가 한 번 실행되나 확인")
    void removePlan_ok(){
        planRemoveService.removePlan(any());
        verify(planRemoveService, times(1)).removePlan(any());
    }

}