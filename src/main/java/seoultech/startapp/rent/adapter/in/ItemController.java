package seoultech.startapp.rent.adapter.in;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import seoultech.startapp.global.response.JsonResponse;
import seoultech.startapp.rent.application.port.in.ItemGetUseCase;
import seoultech.startapp.rent.application.port.in.command.GetItemCountCommand;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rent/item")
class ItemController {

    private final ItemGetUseCase itemGetUseCase;

    @GetMapping("/calendar")
    public ResponseEntity<?> getByAvailable(@RequestParam("category") String itemCategory){
        long itemCount = itemGetUseCase.countByItemCategory(new GetItemCountCommand(itemCategory));
        return JsonResponse.okWithData(HttpStatus.OK,"사용가능한 ItemCategory만 불러왔습니다.",new ItemCountResponse(itemCount));
    }
}
