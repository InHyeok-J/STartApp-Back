package seoultech.startapp.plan.application.port.in;

import org.springframework.data.domain.PageRequest;
import seoultech.startapp.plan.application.PlanPagingResult;

public interface PlanGetUseCase {

    PlanPagingResult getAllPlanByPaging(PageRequest pageRequest);

}
