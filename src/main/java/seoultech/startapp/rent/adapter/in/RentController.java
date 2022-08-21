package seoultech.startapp.rent.adapter.in;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import seoultech.startapp.global.config.web.AuthMember;
import seoultech.startapp.global.config.web.LoginMember;
import seoultech.startapp.global.response.JsonResponse;
import seoultech.startapp.rent.application.RentResponse;
import seoultech.startapp.rent.application.port.in.RentGetUseCase;
import seoultech.startapp.rent.application.port.in.RentRegisterUseCase;
import seoultech.startapp.rent.application.port.in.command.RegisterRentCommand;
import seoultech.startapp.rent.application.port.in.command.RentCalendarCommand;

@RestController
@RequestMapping("/api/rent")
@RequiredArgsConstructor
class RentController {

    private final RentRegisterUseCase rentRegisterUseCase;
    private final RentGetUseCase rentGetUseCase;
    @PostMapping("")
    public ResponseEntity<?> registerRent(@LoginMember AuthMember member, @RequestBody RegisterRentRequest registerRentRequest){
        RegisterRentCommand registerRentCommand = registerRentRequest.toRentCommand(member.getMemberId());
        rentRegisterUseCase.registerRent(registerRentCommand);
        return JsonResponse.ok(HttpStatus.OK,"상시사업 물품 대여 신청이 완료되었습니다.");
    }

    @GetMapping("")
    public ResponseEntity<?> getMyRentList(@LoginMember AuthMember member){
        List<RentResponse> myRentList = rentGetUseCase.getMyRentList(member.getMemberId());
        return JsonResponse.okWithData(HttpStatus.OK,"내 렌트 현황 조회 성공",myRentList);
    }

    @GetMapping("/calendar")
    public ResponseEntity<?> getRentCalendar(
        @RequestParam("month") int month,
        @RequestParam("year") int year,
        @RequestParam("category") String category
    ){
        List<RentResponse> result = rentGetUseCase.getCalendar(RentCalendarCommand.builder()
            .month(month)
            .year(year)
            .itemCategory(category)
            .build());
        return JsonResponse.okWithData(HttpStatus.OK, "조회 성공", result);
    }

}
