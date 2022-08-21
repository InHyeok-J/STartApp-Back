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
import seoultech.startapp.rent.application.port.in.command.GetItemCommand;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rent/item")
public class ItemController {

    private final ItemGetUseCase itemGetUseCase;

    @GetMapping("/calendar")
    public ResponseEntity<?> getByAvailable(@RequestParam("category") String itemCategory){
        Long itemCount = itemGetUseCase.getByItemCategory(new GetItemCommand(itemCategory));
        return JsonResponse.okWithData(HttpStatus.OK,"사용가능한 ItemCategory만 불러왔습니다.",itemCount);
    }
}
