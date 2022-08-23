package seoultech.startapp.suggestion.application.port.in;

public interface SuggestionUseCase {

  void suggest(SuggestCommand command);
  void suggestionCheck(Long suggestionId);
}
