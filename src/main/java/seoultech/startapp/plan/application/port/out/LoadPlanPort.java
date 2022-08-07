package seoultech.startapp.plan.application.port.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import seoultech.startapp.plan.domain.Plan;

import java.util.List;

public interface LoadPlanPort {
    Page<Plan> loadAllPlanByPaging(PageRequest pageRequest);

    List<Plan> getPlanByMonthAndYear(int year,int month);
}
