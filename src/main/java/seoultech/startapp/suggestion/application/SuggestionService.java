package seoultech.startapp.suggestion.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import seoultech.startapp.global.common.S3Uploader;
import seoultech.startapp.suggestion.application.port.in.SuggestCommand;
import seoultech.startapp.suggestion.application.port.in.SuggestionUseCase;
import seoultech.startapp.suggestion.application.port.out.LoadSuggestionPort;
import seoultech.startapp.suggestion.application.port.out.SaveSuggestionPort;
import seoultech.startapp.suggestion.domain.Suggestion;

@Service
@RequiredArgsConstructor
public class SuggestionService implements SuggestionUseCase {

  private final LoadSuggestionPort loadSuggestionPort;
  private final SaveSuggestionPort saveSuggestionPort;
  private final S3Uploader s3Uploader;

  @Transactional
  @Override
  public void suggest(SuggestCommand command) {
    Suggestion suggestion = command.toEntity();
    MultipartFile multipartFile = command.getMultipartFile();
    
    if (multipartFile != null &&
        (StringUtils.hasText(multipartFile.getOriginalFilename()) && multipartFile.getSize() > 0)) {
      String SUGGESTION_S3_PATH = "app/suggestion/";
      String imageUrl = s3Uploader.uploadFile(SUGGESTION_S3_PATH, command.getMultipartFile());
      suggestion.setImageUrl(imageUrl);
    }

    saveSuggestionPort.save(suggestion);
  }

  @Transactional
  @Override
  public void suggestionCheck(Long suggestionId) {
    Suggestion suggestion = loadSuggestionPort.loadById(suggestionId);
    suggestion.check();
    saveSuggestionPort.save(suggestion);
  }
}
