package seoultech.startapp.rent.adapter.in;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seoultech.startapp.global.response.JsonResponse;
import seoultech.startapp.rent.application.port.in.ItemRegisterUseCase;
import seoultech.startapp.rent.application.port.in.command.ItemCommand;

@RestController
@RequestMapping("/api/admin/rent")
@RequiredArgsConstructor
class RentAdminController {

    private final ItemRegisterUseCase itemRegisterUseCase;

    @PostMapping("/item")
    public ResponseEntity<?> registerItem(@RequestBody ItemRequest itemRequest){
        ItemCommand itemCommand = itemRequest.toItemCommand();
        itemRegisterUseCase.registerItem(itemCommand);
        return JsonResponse.ok(HttpStatus.OK,"대여 물품이 정상 등록되었습니다.");
    }
}
