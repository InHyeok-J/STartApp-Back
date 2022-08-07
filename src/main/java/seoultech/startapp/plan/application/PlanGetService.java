package seoultech.startapp.plan.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.plan.application.port.in.PlanGetUseCase;
import seoultech.startapp.plan.application.port.out.LoadPlanPort;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
class PlanGetService implements PlanGetUseCase {

    private final LoadPlanPort loadPlanPort;

    @Override
    @Transactional(readOnly = true)
    public PlanPagingResponse getAllPlanByPaging(PageRequest pageRequest) {

        Page<PlanResponse> planResponses = loadPlanPort.loadAllPlanByPaging(pageRequest)
                                                   .map(PlanResponse::ToPlanResponse);

        return new PlanPagingResponse(planResponses.getTotalPages(), planResponses.getContent());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlanResponse> getPlanByYearAndMonth(int year, int month) {
        return loadPlanPort.getPlanByMonthAndYear(year,month)
                            .stream().map(PlanResponse::ToPlanResponse)
                            .collect(Collectors.toList());
    }
}
