package seoultech.startapp.plan.adapter.in;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import seoultech.startapp.global.response.JsonResponse;
import seoultech.startapp.plan.application.PlanResponse;
import seoultech.startapp.plan.application.port.in.PlanGetUseCase;

import java.util.List;

@RestController
@RequestMapping("/api/v1/plan")
@RequiredArgsConstructor
class PlanController {

    private final PlanGetUseCase planGetUseCase;

    @GetMapping("")
    public ResponseEntity<?> getPlanByYearAndMonth(@RequestParam int year, @RequestParam int month){
        List<PlanResponse> planByYearAndMonth = planGetUseCase.getPlanByYearAndMonth(year, month);
        return JsonResponse.okWithData(HttpStatus.OK,"year과 month에 해당하는 plan을 가져왔습니다.",planByYearAndMonth);
    }
}
