package seoultech.startapp.suggestion.application.port;

import seoultech.startapp.suggestion.application.SuggestionPagingResponse;
import seoultech.startapp.suggestion.application.port.in.SuggestionPagingCommand;

public interface SuggestionGetUseCase {

  SuggestionPagingResponse getByPagingAndCategory(SuggestionPagingCommand command);
}
