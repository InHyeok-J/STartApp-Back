package seoultech.startapp.suggestion.application.port.in;

import java.util.HashSet;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;
import seoultech.startapp.global.common.SelfValidator;
import seoultech.startapp.suggestion.domain.Suggestion;
import seoultech.startapp.suggestion.domain.SuggestionCategory;

@Getter
public class SuggestCommand extends SelfValidator<SuggestCommand> {

  @NotBlank
  @Size(min = 1, max = 20)
  private String title;

  @NotBlank
  @Size(min = 1, max = 500)
  private String content;

  private MultipartFile multipartFile;

  @NotNull
  private SuggestionCategory category;

  @Builder
  public SuggestCommand(String title, String content,
      MultipartFile multipartFile, String category) {
    this.title = title;
    this.content = content;
    this.multipartFile = multipartFile;
    this.category = validationCategory(category);
  }

  private SuggestionCategory validationCategory(String category){
    try{
      return SuggestionCategory.valueOf(category);
    }catch (Exception e){
      throw new ConstraintViolationException(new HashSet<>());
    }
  }

  public Suggestion toEntity(){
    return Suggestion.builder()
        .title(title)
        .content(content)
        .suggestionCategory(category)
        .isCheck(false)
        .build();
  }
}
