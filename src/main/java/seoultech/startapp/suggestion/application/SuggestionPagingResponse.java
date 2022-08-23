package seoultech.startapp.suggestion.application;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SuggestionPagingResponse {

  private int totalPage;
  private List<SuggestionResponse> suggestionList;

  @Builder
  public SuggestionPagingResponse(int totalPage,
      List<SuggestionResponse> suggestionList) {
    this.totalPage = totalPage;
    this.suggestionList = suggestionList;
  }
}
