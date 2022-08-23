package seoultech.startapp.suggestion.application.port.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import seoultech.startapp.suggestion.domain.Suggestion;
import seoultech.startapp.suggestion.domain.SuggestionCategory;

public interface LoadSuggestionPort {

  Suggestion loadById(Long suggestionId);
  Page<Suggestion> loadByPaging(PageRequest pageRequest, SuggestionCategory category);
}
