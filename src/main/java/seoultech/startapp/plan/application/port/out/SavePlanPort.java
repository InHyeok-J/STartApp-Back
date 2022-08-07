package seoultech.startapp.plan.application.port.out;

import seoultech.startapp.plan.domain.Plan;

public interface SavePlanPort {

    void savePlan(Plan plan);
}
