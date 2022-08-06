package seoultech.startapp.plan.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.plan.application.port.in.PlanGetUseCase;
import seoultech.startapp.plan.application.port.out.LoadPlanPagingPort;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
class PlanGetService implements PlanGetUseCase {

    private final LoadPlanPagingPort loadPlanPagingPort;

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> getAllPlanByPaging(PageRequest pageRequest) {

        Page<PlanResponse> planResponses = loadPlanPagingPort.loadAllPlanByPaging(pageRequest)
                                                   .map(PlanResponse::ToPlanResponse);

        Map<String,Object> pageResult = new HashMap<>();

        pageResult.put("totalPage",planResponses.getTotalPages());
        pageResult.put("planList",planResponses.getContent());

        return pageResult;
    }
}
