package seoultech.startapp.plan.application.port.in;

import seoultech.startapp.plan.application.PlanPagingResponse;
import seoultech.startapp.plan.application.PlanResponse;

import java.util.List;

public interface PlanGetUseCase {

    PlanPagingResponse getAllPlanByPaging(int page,int count);

    List<PlanResponse> getPlanByYearAndMonth(int year,int month);

}
