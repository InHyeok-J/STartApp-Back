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
import seoultech.startapp.rent.application.port.in.ItemGetUseCase;
import seoultech.startapp.rent.application.port.in.ItemRegisterUseCase;
import seoultech.startapp.rent.application.port.in.ItemUpdateUseCase;
import seoultech.startapp.rent.application.port.in.command.ItemPagingCommand;
import seoultech.startapp.rent.application.port.in.command.UpdateItemAvailableCommand;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/rent/item")
public class ItemAdminController {

  private final ItemRegisterUseCase itemRegisterUseCase;
  private final ItemGetUseCase itemGetUseCase;
  private final ItemUpdateUseCase itemUpdateUseCase;

  @PostMapping("")
  public ResponseEntity<?> registerItem(@RequestBody RegisterItemRequest request) {
    itemRegisterUseCase.registerItem(request.toItemCommand());
    return JsonResponse.ok(HttpStatus.OK, "대여 물품이 정상 등록되었습니다.");
  }

  @GetMapping("/list")
  public ResponseEntity<?> getItemByPaging(
      @RequestParam("page") int page,
      @RequestParam(value = "count", defaultValue = "10", required = false) int count,
      @RequestParam(value = "category", defaultValue = "ALL", required = false) String itemCategory)
  {
    ItemPagingCommand itemPagingCommand = ItemPagingCommand.builder()
        .page(page)
        .count(count)
        .itemCategory(itemCategory).build();
    ItemPagingResponse itemPagingResponse = itemGetUseCase.getByPaging(itemPagingCommand);

    return JsonResponse.okWithData(HttpStatus.OK,"페이지에 해당하는 상시사업 물품을 불러왔습니다",itemPagingResponse);
  }

  @PatchMapping("/item")
  public ResponseEntity<?> updateItemByAvailable(@RequestBody UpdateItemAvailableRequest updateItemAvailableRequest){
    UpdateItemAvailableCommand updateItemAvailableCommand = updateItemAvailableRequest.ToItemCommand();
    itemUpdateUseCase.updateByAvailable(updateItemAvailableCommand);
    return JsonResponse.ok(HttpStatus.OK,"해당 상시사업 물품의 사용 가능 여부를 변경했습니다");
  }
}
