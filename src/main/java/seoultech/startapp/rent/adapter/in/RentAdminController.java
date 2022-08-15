package seoultech.startapp.rent.adapter.in;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import seoultech.startapp.global.response.JsonResponse;
import seoultech.startapp.rent.application.ItemPagingResponse;
import seoultech.startapp.rent.application.RentPagingResponse;
import seoultech.startapp.rent.application.port.in.ItemGetUseCase;
import seoultech.startapp.rent.application.port.in.ItemRegisterUseCase;
import seoultech.startapp.rent.application.port.in.ItemUpdateUseCase;
import seoultech.startapp.rent.application.port.in.RentGetUseCase;
import seoultech.startapp.rent.application.port.in.command.RegisterItemCommand;
import seoultech.startapp.rent.application.port.in.command.UpdateItemAvailableCommand;

@RestController
@RequestMapping("/api/admin/rent")
@RequiredArgsConstructor
class RentAdminController {

    private final ItemRegisterUseCase itemRegisterUseCase;
    private final ItemGetUseCase itemGetUseCase;
    private final ItemUpdateUseCase itemUpdateUseCase;
    private final RentGetUseCase rentGetUseCase;

    @PostMapping("/item")
    public ResponseEntity<?> registerItem(@RequestBody RegisterItemRequest registerItemRequest){
        RegisterItemCommand registerItemCommand = registerItemRequest.toItemCommand();
        itemRegisterUseCase.registerItem(registerItemCommand);
        return JsonResponse.ok(HttpStatus.OK,"대여 물품이 정상 등록되었습니다.");
    }

    @GetMapping("/item/list")
    public ResponseEntity<?> getItemByPaging(@RequestParam("page") int page,
                                             @RequestParam(value = "count",defaultValue = "10",required = false) int count,
                                             @RequestParam(value = "category",defaultValue = "ALL",required = false) String itemCategory){
        ItemPagingResponse itemPagingResponse = itemGetUseCase.getByPaging(page, count,itemCategory);
        return JsonResponse.okWithData(HttpStatus.OK,"페이지에 해당하는 상시사업 물품을 불러왔습니다",itemPagingResponse);
    }
    @PatchMapping("/item")
    public ResponseEntity<?> updatedByAvailable(@RequestBody UpdateItemAvailableRequest updateItemAvailableRequest){
        UpdateItemAvailableCommand updateItemAvailableCommand = updateItemAvailableRequest.ToItemCommand();
        itemUpdateUseCase.updateByAvailable(updateItemAvailableCommand);
        return JsonResponse.ok(HttpStatus.OK,"해당 상시사업 물품의 사용 여부를 변경했습니다");
    }
    @GetMapping("/list")
    public ResponseEntity<?> getRentByPaging(@RequestParam("page") int page,
                                             @RequestParam(value = "count",defaultValue = "10",required = false) int count,
                                             @RequestParam(value = "status",defaultValue = "ALL",required = false) String rentStatus){
        RentPagingResponse rentPagingResponse = rentGetUseCase.getByPaging(page, count, rentStatus);
        return JsonResponse.okWithData(HttpStatus.OK,"페이지에 해당하는 Rent를 불러왔습니다.",rentPagingResponse);
    }
}
