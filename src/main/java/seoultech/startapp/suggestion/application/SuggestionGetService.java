package seoultech.startapp.suggestion.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.suggestion.application.port.SuggestionGetUseCase;
import seoultech.startapp.suggestion.application.port.in.SuggestionPagingCommand;
import seoultech.startapp.suggestion.application.port.out.LoadSuggestionPort;
import seoultech.startapp.suggestion.domain.Suggestion;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SuggestionGetService implements SuggestionGetUseCase {

  private final LoadSuggestionPort loadSuggestionPort;

  @Override
  public SuggestionPagingResponse getByPagingAndCategory(SuggestionPagingCommand command) {
    PageRequest pageRequest = PageRequest.of(command.getPage(), command.getCount());
    Page<Suggestion> suggestions = loadSuggestionPort.loadByPaging(
        pageRequest,
        command.getCategory());

    return SuggestionPagingResponse.builder()
        .suggestionList(suggestions.getContent().stream().map(SuggestionResponse::toDto).toList())
        .totalPage(suggestions.getTotalPages())
        .build();
  }
}
