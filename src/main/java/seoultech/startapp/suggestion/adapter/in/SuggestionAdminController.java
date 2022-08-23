package seoultech.startapp.suggestion.adapter.in;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import seoultech.startapp.global.response.JsonResponse;
import seoultech.startapp.suggestion.application.SuggestionPagingResponse;
import seoultech.startapp.suggestion.application.port.SuggestionGetUseCase;
import seoultech.startapp.suggestion.application.port.in.SuggestionPagingCommand;
import seoultech.startapp.suggestion.application.port.in.SuggestionUseCase;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/suggestion")
public class SuggestionAdminController {

  private final SuggestionUseCase suggestionUseCase;
  private final SuggestionGetUseCase suggestionGetUseCase;

  @GetMapping("/list")
  public ResponseEntity<?> getList(
      @RequestParam("page") int page,
      @RequestParam(value = "count", defaultValue = "10", required = false) int count,
      @RequestParam(value = "category", defaultValue = "ALL", required = false) String category
  ) {
    SuggestionPagingResponse result = suggestionGetUseCase.getByPagingAndCategory(
        SuggestionPagingCommand.builder()
            .page(page)
            .count(count)
            .category(category)
            .build()
    );
    return JsonResponse.okWithData(HttpStatus.OK, "조회 성공", result);
  }

  @PatchMapping("/check/{id}")
  public ResponseEntity<?> check(@PathVariable("id") Long suggestionId){
    suggestionUseCase.suggestionCheck(suggestionId);
    return JsonResponse.ok(HttpStatus.OK, "확인 성공");
  }
}
