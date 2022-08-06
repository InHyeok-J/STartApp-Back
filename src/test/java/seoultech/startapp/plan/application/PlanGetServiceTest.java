package seoultech.startapp.plan.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import seoultech.startapp.plan.application.port.out.LoadPlanPagingPort;
import seoultech.startapp.plan.domain.Plan;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlanGetServiceTest {

    @Mock
    private LoadPlanPagingPort loadPlanPagingPort;

    @InjectMocks
    private PlanGetService planGetService;

    private final int TOTAL_PAGE = 5;
    private final int PAGE = 0;
    private final int COUNT = 10;
    private List<Plan> planList = new ArrayList<>();
    private Map<String,Object> pageResult = new HashMap<>();
    private Page<Plan> planPage;
    private PageRequest pageRequest = PageRequest.of(PAGE,COUNT);;


    @BeforeEach
    void setUp(){
        for(int i = 1;i<=50;i++){

            Plan plan = Plan.builder()
                             .planId(Long.valueOf(i))
                             .planName("planName" + i)
                             .startTime(LocalDateTime.now().plusDays(i))
                             .endTime(LocalDateTime.now().plusDays(i + 1))
                             .build();

            planList.add(plan);
        }

        planPage = new PageImpl<>(planList,pageRequest,50);

        pageResult.put("totalPage",TOTAL_PAGE);
    }

    @Test
    @DisplayName("페이지네이션의 totalPage 확인")
    void getPlanPaging_ok(){


        when(loadPlanPagingPort.loadAllPlanByPaging(pageRequest))
            .thenReturn(planPage);

        PlanPagingResult allPlanByPaging = planGetService.getAllPlanByPaging(pageRequest);

        assertThat(allPlanByPaging.getTotalPage()).isEqualTo(pageResult.get("totalPage"));
    }

}