package seoultech.startapp.plan.adapter.in;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import seoultech.startapp.global.response.JsonResponse;
import seoultech.startapp.plan.application.PlanPagingResponse;
import seoultech.startapp.plan.application.port.in.PlanCommand;
import seoultech.startapp.plan.application.port.in.PlanGetUseCase;
import seoultech.startapp.plan.application.port.in.PlanRegisterUseCase;
import seoultech.startapp.plan.application.port.in.PlanRemoveUseCase;

@Slf4j
@RestController
@RequestMapping("/api/admin/plan")
@RequiredArgsConstructor
class PlanAuthController {

    private final PlanGetUseCase planGetUseCase;
    private final PlanRemoveUseCase planRemoveUseCase;
    private final PlanRegisterUseCase planRegisterUseCase;

    @GetMapping("")
    public ResponseEntity<?> getPlanByPaging(@RequestParam int page, @RequestParam(required = false,defaultValue = "10") int count){

        PlanPagingResponse allPlanByPaging = planGetUseCase.getAllPlanByPaging(page,count);

        return JsonResponse.okWithData(HttpStatus.OK,"페이지에 해당하는 plan을 찾았습니다.",allPlanByPaging);
    }

    @PostMapping("")
    public ResponseEntity<?> registerPlan(@RequestBody PlanRequest planRequest){

        PlanCommand planCommand = planRequest.ToPlanCommand();

        planRegisterUseCase.register(planCommand);
        return JsonResponse.ok(HttpStatus.CREATED, "학사일정을 생성했습니다.");
    }

    @DeleteMapping("/{planId}")
    public ResponseEntity<?> removePlan(@PathVariable("planId") Long planId){
        planRemoveUseCase.removePlan(planId);
        return JsonResponse.ok(HttpStatus.OK,"선택한 학사일정을 제거했습니다.");
    }
}
