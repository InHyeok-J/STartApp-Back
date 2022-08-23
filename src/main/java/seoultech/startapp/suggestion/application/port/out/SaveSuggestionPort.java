package seoultech.startapp.suggestion.application.port.out;

import seoultech.startapp.suggestion.domain.Suggestion;

public interface SaveSuggestionPort {

  void save(Suggestion suggestion);
}
