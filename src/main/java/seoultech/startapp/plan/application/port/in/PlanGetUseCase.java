package seoultech.startapp.plan.application.port.in;

import org.springframework.data.domain.PageRequest;

import java.util.Map;

public interface PlanGetUseCase {

    Map<String,Object> getAllPlanByPaging(PageRequest pageRequest);

}
