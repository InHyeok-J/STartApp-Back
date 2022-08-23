package seoultech.startapp.suggestion.application.port.in;

import java.util.HashSet;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import seoultech.startapp.global.common.SelfValidator;
import seoultech.startapp.suggestion.domain.SuggestionCategory;

@Getter
public class SuggestionPagingCommand extends SelfValidator<SuggestionPagingCommand> {

  @NotNull
  @Min(0)
  private int page;

  @NotNull
  @Min(1)
  private int count;

  @NotNull
  private SuggestionCategory category;

  @Builder
  public SuggestionPagingCommand(int page, int count,
      String category) {
    this.page = page;
    this.count = count;
    this.category = validationCategory(category);
  }


  private SuggestionCategory validationCategory(String category){
    try{
      return SuggestionCategory.valueOf(category);
    }catch (Exception e){
      throw new ConstraintViolationException(new HashSet<>());
    }
  }
}
