package seoultech.startapp.member.adapter.in;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import javax.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import seoultech.startapp.global.response.JsonResponse;
import seoultech.startapp.member.application.SeoulTechAuthResponse;
import seoultech.startapp.member.application.port.in.SeoulTechAuthUseCase;
import seoultech.startapp.member.application.port.in.command.SeoulTechAuthCommand;

@Slf4j
@Controller
@Validated
@RequiredArgsConstructor
public class SeoulTechAuthController {

  private final SeoulTechAuthUseCase seoulTechAuthUseCase;

  @PostMapping("/api/v1/auth/seoultech")
  public String seoulTechAuth(
      @RequestParam(value = "key", required = false) String keys,
      @RequestParam(value = "returnJson", required = false) String encodedValue
      ) throws JsonProcessingException {
    if (!checkParams( keys, encodedValue) || getStudentNo(encodedValue) == null) {
      // 두 값이 null 이거나, JsonParse에서 실패해서 null을 리턴 받은 경우
      return "redirect:/api/v1/auth/loading";
    }

    String studentNo = getStudentNo(encodedValue);

    SeoulTechAuthCommand command = SeoulTechAuthCommand.builder()
        .jsonValue(encodedValue)
        .studentNo(studentNo)
        .key(keys)
        .build();

    seoulTechAuthUseCase.authSeoulTech(command);

    return "redirect:/api/v1/auth/loading";
  }

  @GetMapping("/api/v1/auth/loading")
  public String loadingPage() {
    return "Loading";
  }

  @GetMapping("/api/v1/auth/seoultech/check")
  public ResponseEntity<?> checkAuth(@RequestParam(value = "key")  @NotBlank String key){
    SeoulTechAuthResponse seoulTechAuthResponse = seoulTechAuthUseCase.checkAuth(key);
    return JsonResponse.okWithData(HttpStatus.OK,"인증 성공", seoulTechAuthResponse);
  }

  private boolean checkParams( String keys, String encodedValue) {
    if ( !StringUtils.hasText(keys)  || !StringUtils.hasText(encodedValue)) {
      log.error(" 키 : " + keys + " 인코딩 벨류: " + encodedValue);
      return false ;
    }
    return true;
  }

  private String getStudentNo(String encodedValue){
    try {
      String decodedValue = new String(Base64.getDecoder().decode(encodedValue),
          StandardCharsets.UTF_8);
      ObjectMapper mapper = new ObjectMapper();
      Map<String, String> map = mapper.readValue(decodedValue, Map.class);
      return map.get("STNT_NUMB");
    }catch (Exception e){
      return null;
    }
  }
}
