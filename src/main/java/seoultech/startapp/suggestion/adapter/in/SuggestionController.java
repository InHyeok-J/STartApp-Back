package seoultech.startapp.suggestion.adapter.in;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import seoultech.startapp.global.response.JsonResponse;
import seoultech.startapp.suggestion.application.port.in.SuggestCommand;
import seoultech.startapp.suggestion.application.port.in.SuggestionUseCase;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/suggestion")
public class SuggestionController {

  private final SuggestionUseCase suggestionUseCase;

  @PostMapping("")
  public ResponseEntity<?> suggest(
      @RequestParam("title") String title,
      @RequestParam("content") String content,
      @RequestParam(value = "file", required = false) MultipartFile multipartFile,
      @RequestParam("category") String category
  ) {
    suggestionUseCase.suggest(SuggestCommand.builder()
        .title(title)
        .content(content)
        .category(category)
        .multipartFile(multipartFile)
        .build());

    return JsonResponse.ok(HttpStatus.CREATED, "제안하기 성공");
  }

}
