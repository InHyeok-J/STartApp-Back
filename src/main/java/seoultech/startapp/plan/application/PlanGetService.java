package seoultech.startapp.plan.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.plan.application.port.in.PlanGetUseCase;
import seoultech.startapp.plan.application.port.out.LoadPlanPagingPort;

@Slf4j
@Service
@RequiredArgsConstructor
class PlanGetService implements PlanGetUseCase {

    private final LoadPlanPagingPort loadPlanPagingPort;

    @Override
    @Transactional(readOnly = true)
    public PlanPagingResult getAllPlanByPaging(PageRequest pageRequest) {

        Page<PlanResponse> planResponses = loadPlanPagingPort.loadAllPlanByPaging(pageRequest)
                                                   .map(PlanResponse::ToPlanResponse);

        return new PlanPagingResult(planResponses.getTotalPages(),planResponses.getContent());
    }
}
