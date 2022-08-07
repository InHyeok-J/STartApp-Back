package seoultech.startapp.plan.adapter.out;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import seoultech.startapp.plan.application.port.out.LoadPlanPort;
import seoultech.startapp.plan.application.port.out.RemovePlanPort;
import seoultech.startapp.plan.application.port.out.SavePlanPort;
import seoultech.startapp.plan.domain.Plan;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PlanPersistenceAdapter implements RemovePlanPort, SavePlanPort, LoadPlanPort {
    private final JpaPlanRepository jpaPlanRepository;
    private final JpaPlanQueryRepository jpaPlanQueryRepository;
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
    public List<Plan> getPlanByMonthAndYear(int year, int month) {
        LocalDateTime startTime = LocalDateTime.of(year,month,1,00,00);
        LocalDateTime endTime = createEndTime(year,month);

        return jpaPlanQueryRepository.findPlanByYearAndMonth(startTime,endTime);
    }

    private LocalDateTime createEndTime(int year, int month) {
        int dayOfMonth = 31;

        switch (month){
            case 4:
            case 6:
            case 9:
            case 11:
                dayOfMonth = 30;
                break;
            case 2:
                dayOfMonth = 28;
                break;
        }

        return LocalDateTime.of(year,month,dayOfMonth,23,59);
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
