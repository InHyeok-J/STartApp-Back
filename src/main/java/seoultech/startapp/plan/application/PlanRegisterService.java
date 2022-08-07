package seoultech.startapp.plan.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.plan.application.port.in.PlanCommand;
import seoultech.startapp.plan.application.port.in.PlanRegisterUseCase;
import seoultech.startapp.plan.application.port.out.SavePlanPort;
import seoultech.startapp.plan.domain.Plan;

@Slf4j
@Service
@RequiredArgsConstructor
class PlanRegisterService implements PlanRegisterUseCase {

    private final SavePlanPort savePlanPort;
    @Override
    @Transactional
    public void register(PlanCommand planCommand) {
        Plan plan = PlanCommand.ToDomainPlan(planCommand);
        savePlanPort.savePlan(plan);
    }
}
