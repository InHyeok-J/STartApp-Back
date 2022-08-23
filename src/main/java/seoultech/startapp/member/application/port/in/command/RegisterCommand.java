package seoultech.startapp.member.application.port.in.command;

import java.util.HashSet;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;
import seoultech.startapp.global.common.SelfValidator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@EqualsAndHashCode(callSuper = false)
public class RegisterCommand extends SelfValidator<RegisterCommand> {

  @NotBlank
  private final String StudentNo;

  @NotBlank
  @Pattern(regexp = "[a-zA-Z0-9~!@#$%^&*()]{8,16}")
  private final String appPassword;

  @NotBlank
  private final String name;

  @NotBlank
  private final String department;

  @NotBlank
  private final String fcmToken;

  @NotNull
  private final MultipartFile file;

  @Builder
  public RegisterCommand(String studentNo, String appPassword, String name,
      String department, String fcmToken, MultipartFile file) {
    this.StudentNo = studentNo;
    this.appPassword = appPassword;
    this.name = name;
    this.department = department;
    this.fcmToken = fcmToken;
    this.file = file;
    validationFile(file);
    this.validateSelf();
  }

  private void validationFile(MultipartFile file){
    if(file.getSize() == 0){
      throw new ConstraintViolationException(new HashSet<>());
    }
  }


}
