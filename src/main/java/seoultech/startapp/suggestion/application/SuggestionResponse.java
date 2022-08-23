package seoultech.startapp.suggestion.application;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import seoultech.startapp.suggestion.domain.Suggestion;
import seoultech.startapp.suggestion.domain.SuggestionCategory;

@Getter
public class SuggestionResponse {

  private Long suggestionId;
  private String title;
  private String content;
  private String imageUrl;
  private Boolean isCheck;
  private SuggestionCategory category;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  @Builder
  public SuggestionResponse(Long suggestionId, String title, String content,
      String imageUrl, Boolean isCheck,
      SuggestionCategory category, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.suggestionId = suggestionId;
    this.title = title;
    this.content = content;
    this.imageUrl = imageUrl;
    this.isCheck = isCheck;
    this.category = category;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public static SuggestionResponse toDto(Suggestion suggestion){
    return SuggestionResponse.builder()
        .suggestionId(suggestion.getSuggestionId())
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
