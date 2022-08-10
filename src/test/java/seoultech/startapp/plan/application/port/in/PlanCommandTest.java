package seoultech.startapp.plan.application.port.in;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import seoultech.startapp.plan.domain.Plan;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class PlanCommandTest {

    private PlanCommand planCommand;

    private Plan plan;

    @BeforeEach
    void setUp(){
        planCommand = PlanCommand.builder()
                                 .planName("planTitle")
                                 .color("#FFFFFFFF")
                                 .startTime(LocalDateTime.now())
                                 .endTime(LocalDateTime.now().plusDays(1))
                                 .build();

        plan = Plan.builder()
                   .planName("planTitle")
                   .color("#FFFFFFFF")
                   .startTime(LocalDateTime.now())
                   .endTime(LocalDateTime.now().plusDays(1))
                   .build();
    }

    @Test
    @DisplayName("Command가 Domain으로 제대로 변환되었는 지 확인")
    void commandToDomain_ok(){
        Plan testPlan = PlanCommand.ToDomainPlan(planCommand);

        assertThat(testPlan.getPlanName()).isEqualTo(plan.getPlanName());
        assertThat(testPlan.getColor()).isEqualTo(plan.getColor());
    }

}