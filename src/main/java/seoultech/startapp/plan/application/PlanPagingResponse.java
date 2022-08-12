package seoultech.startapp.plan.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlanPagingResponse {

    private int totalPage;

    private List<PlanResponse> planList;
}
