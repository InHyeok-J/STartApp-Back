package seoultech.startapp.suggestion.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import seoultech.startapp.global.common.S3Uploader;
import seoultech.startapp.suggestion.application.port.in.SuggestCommand;
import seoultech.startapp.suggestion.application.port.out.LoadSuggestionPort;
import seoultech.startapp.suggestion.application.port.out.SaveSuggestionPort;
import seoultech.startapp.suggestion.domain.Suggestion;

@ExtendWith(MockitoExtension.class)
class SuggestionServiceTest {

  @Mock
  LoadSuggestionPort loadSuggestionPort;

  @Mock
  SaveSuggestionPort saveSuggestionPort;

  @Mock
  S3Uploader s3Uploader;

  @InjectMocks
  SuggestionService suggestionService;

  MultipartFile mockImage = new MockMultipartFile("data", "filename.png", "image/png", "image.png".getBytes());

  @Test
  @DisplayName("제안하기 요청 시 imgaeFile이 null 이면 s3업로드를 안한다.")
  public void suggest_imageNull() throws Exception {
    SuggestCommand imageUrlNullCommand = SuggestCommand.builder()
        .title("제목")
        .content("내용")
        .category("ETC")
        .build();

    suggestionService.suggest(imageUrlNullCommand);

    verify(s3Uploader, times(0)).uploadFile(any(), any());
    verify(saveSuggestionPort, times(1)).save(any());
  }

  @Test
  @DisplayName("제안하기 시 imageFile이 있으면 s3Upload를 한다.")
  public void suggest_imageOk() throws Exception {
    SuggestCommand imageNotNullCommand = SuggestCommand.builder()
        .title("제목")
        .content("내용")
        .category("ETC")
        .multipartFile(mockImage)
        .build();

    suggestionService.suggest(imageNotNullCommand);

    verify(s3Uploader, times(1)).uploadFile(any(), any());
    verify(saveSuggestionPort, times(1)).save(any());
  }

  @Test
  @DisplayName("제안하기 체크 시 isCheck가 true로 바뀐다.")
  public void suggetion_check_true() throws Exception {
    final Long suggestionId = 1L;
    Suggestion mockSuggestion = Suggestion.builder()
        .suggestionId(suggestionId)
        .title("제목")
        .isCheck(false)
        .build();
    given(loadSuggestionPort.loadById(suggestionId)).willReturn(mockSuggestion);

    suggestionService.suggestionCheck(suggestionId);

    assertTrue(mockSuggestion.getIsCheck());
  }
}