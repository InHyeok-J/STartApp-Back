package seoultech.startapp.suggestion.adapter.out;

import org.springframework.stereotype.Component;
import seoultech.startapp.suggestion.domain.Suggestion;

@Component
public class SuggestionMapper {

  public Suggestion toDomainSuggestion(JpaSuggestion jpaSuggestion) {
    return Suggestion.builder()
        .suggestionId(jpaSuggestion.getId())
        .title(jpaSuggestion.getTitle())
        .content(jpaSuggestion.getContent())
        .imageUrl(jpaSuggestion.getImageUrl())
        .isCheck(jpaSuggestion.getIsCheck())
        .suggestionCategory(jpaSuggestion.getCategory())
        .createdAt(jpaSuggestion.getCreatedAt())
        .updatedAt(jpaSuggestion.getUpdatedAt())
        .build();
  }

  public JpaSuggestion toJpaSuggestion(Suggestion suggestion) {
    return JpaSuggestion.builder()
        .id(suggestion.getSuggestionId() == null ? null : suggestion.getSuggestionId())
        .title(suggestion.getTitle())
        .content(suggestion.getContent())
        .imageUrl(suggestion.getImageUrl())
        .isCheck(suggestion.getIsCheck())
        .category(suggestion.getSuggestionCategory())
        .createdAt(suggestion.getCreatedAt())
        .updatedAt(suggestion.getUpdatedAt())
        .build();
  }
}
