package seoultech.startapp.plan.application.port.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import seoultech.startapp.plan.domain.Plan;

public interface LoadPlanPagingPort {

    Page<Plan> loadAllPlanByPaging(PageRequest pageRequest);
}
