package seoultech.startapp.rent.adapter.in;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seoultech.startapp.global.response.JsonResponse;
import seoultech.startapp.rent.application.port.in.RentCommand;
import seoultech.startapp.rent.application.port.in.RentGetUseCase;
import seoultech.startapp.rent.application.port.in.RentRegisterUseCase;

@RestController
@RequestMapping("/api/rent")
@RequiredArgsConstructor
class RentController {

    private final RentGetUseCase rentGetUseCase;
    private final RentRegisterUseCase rentRegisterUseCase;

    @PostMapping("")
    public ResponseEntity<?> registerRent(@RequestBody RentRequest rentRequest){
        RentCommand rentCommand = rentRequest.toRentCommand();
        rentRegisterUseCase.registerRent(rentCommand);
        return JsonResponse.ok(HttpStatus.OK,"상시사업 물품 대여 신청이 완료되었습니다.");
    }
}
