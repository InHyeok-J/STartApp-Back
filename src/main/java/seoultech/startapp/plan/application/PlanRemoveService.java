package seoultech.startapp.plan.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.plan.application.port.in.PlanRemoveUseCase;
import seoultech.startapp.plan.application.port.out.RemovePlanPort;

@Slf4j
@Service
@RequiredArgsConstructor
class PlanRemoveService implements PlanRemoveUseCase {

    private final RemovePlanPort removePlanPort;
    @Override
    @Transactional
    public void removePlan(Long planId) {
        removePlanPort.deletePlan(planId);
    }
}
