package seoultech.startapp.plan.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import seoultech.startapp.plan.application.port.in.PlanCommand;
import seoultech.startapp.plan.application.port.out.SavePlanPort;
import seoultech.startapp.plan.domain.Plan;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PlanRegisterServiceTest {

    @Mock
    private SavePlanPort savePlanPort;

    @InjectMocks
    private PlanRegisterService planRegisterService;

    private static PlanCommand planCommand;

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
    @DisplayName("savePort의 save가 이 1번 제대로 실행 되었는 지 확인")
    void registerPlan_ok(){
        planRegisterService.register(planCommand);

        Plan testPlan = PlanCommand.ToDomainPlan(planCommand);

        verify(savePlanPort, times(1)).savePlan(refEq(testPlan));
    }


}