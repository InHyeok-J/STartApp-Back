package seoultech.startapp.plan.adapter.out;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import seoultech.startapp.plan.application.port.out.LoadPlanPagingPort;
import seoultech.startapp.plan.application.port.out.RemovePlanPort;
import seoultech.startapp.plan.application.port.out.SavePlanPort;
import seoultech.startapp.plan.domain.Plan;

@Component
@RequiredArgsConstructor
public class PlanAdminPersistenceAdapter implements LoadPlanPagingPort, RemovePlanPort, SavePlanPort {

    private final JpaPlanRepository jpaPlanRepository;

    private final PlanMapper planMapper;
    @Override
    public void savePlan(Plan plan) {
        JpaPlan jpaPlan = planMapper.mapToJpaPlan(plan);
        jpaPlanRepository.save(jpaPlan);
    }

    @Override
    public Page<Plan> loadAllPlanByPaging(PageRequest pageRequest) {
        Page<JpaPlan> jpaPlanPage = jpaPlanRepository.findAllByOrderByStartTimeDesc(pageRequest);
        return planMapper.mapToDomainPlanPage(jpaPlanPage);

    }

    @Override
    public void deletePlan(Long planId) {
        checkCanDelete(planId);
        jpaPlanRepository.deleteById(planId);
    }

    private void checkCanDelete(Long planId) {
        jpaPlanRepository.findById(planId)
                         .orElseThrow(() -> new NotFoundJpaPlanException("삭제할 planId에 해당하는 plan이 존재하지 않습니다."));
    }
}
