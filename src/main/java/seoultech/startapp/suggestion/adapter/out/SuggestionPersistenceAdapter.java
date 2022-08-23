package seoultech.startapp.suggestion.adapter.out;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import seoultech.startapp.suggestion.application.port.out.LoadSuggestionPort;
import seoultech.startapp.suggestion.application.port.out.SaveSuggestionPort;
import seoultech.startapp.suggestion.domain.Suggestion;
import seoultech.startapp.suggestion.domain.SuggestionCategory;

@Component
@RequiredArgsConstructor
public class SuggestionPersistenceAdapter implements LoadSuggestionPort, SaveSuggestionPort {

  private final JpaSuggestionRepository jpaSuggestionRepository;
  private final SuggestionMapper suggestionMapper;
  private final JpaSuggestionQueryRepository jpaSuggestionQueryRepository;

  @Override
  public Suggestion loadById(Long suggestionId) {
    JpaSuggestion jpaSuggestion = jpaSuggestionRepository.findById(suggestionId)
        .orElseThrow(() -> new NotFoundSuggestionException("해당 문의를 찾을 수 없습니다."));
    return suggestionMapper.toDomainSuggestion(jpaSuggestion);
  }

  @Override
  public Page<Suggestion> loadByPaging(PageRequest pageRequest, SuggestionCategory category) {
    return jpaSuggestionQueryRepository.findAllOrderByIsCheck(pageRequest,category)
        .map(suggestionMapper::toDomainSuggestion);
  }

  @Override
  public void save(Suggestion suggestion) {
    JpaSuggestion jpaSuggestion = suggestionMapper.toJpaSuggestion(suggestion);
    jpaSuggestionRepository.save(jpaSuggestion);
  }
}
