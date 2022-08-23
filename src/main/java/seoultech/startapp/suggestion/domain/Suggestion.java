package seoultech.startapp.suggestion.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Suggestion {

  private Long suggestionId;

  private String title;

  private String content;

  private String imageUrl;

  private Boolean isCheck;

  private SuggestionCategory suggestionCategory;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  @Builder
  public Suggestion(Long suggestionId, String title, String content, String imageUrl,
      Boolean isCheck, SuggestionCategory suggestionCategory, LocalDateTime createdAt,
      LocalDateTime updatedAt) {
    this.suggestionId = suggestionId;
    this.title = title;
    this.content = content;
    this.imageUrl = imageUrl;
    this.isCheck = isCheck;
    this.suggestionCategory = suggestionCategory;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public void setImageUrl(String url){
    this.imageUrl = url;
  }

  public void check(){
    this.isCheck = true;
  }
}
