package seoultech.startapp.plan.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import seoultech.startapp.plan.application.port.in.PlanCommand;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
class PlanRegisterServiceTest {

    @Mock
    private PlanRegisterService planRegisterService;

    private PlanCommand planCommand;

    private PlanCommand planColorWrongCommand;

    @BeforeEach
    void setUp(){
        planCommand = PlanCommand.builder()
            .planName("planTitle")
            .color("#FFFFFFFF")
            .startTime(LocalDateTime.now())
            .endTime(LocalDateTime.now().plusDays(1))
            .build();
    }

    @Test
    @DisplayName("registerPlan이 1번 제대로 실행 되었는 지 확인")
    void registerPlan_ok(){
        planRegisterService.register(planCommand);

        BDDMockito.verify(planRegisterService, Mockito.times(1)).register(planCommand);
    }


}