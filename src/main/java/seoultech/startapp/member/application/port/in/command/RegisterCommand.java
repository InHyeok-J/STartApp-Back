package seoultech.startapp.member.application.port.in.command;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
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
  private final String phoneNo;

  @NotBlank
  private final String fcmToken;

  @Builder
  public RegisterCommand(String studentNo, String appPassword, String name,
      String department, String phoneNo, String fcmToken) {
    this.StudentNo = studentNo;
    this.appPassword = appPassword;
    this.name = name;
    this.department = department;
    this.phoneNo = phoneNo;
    this.fcmToken = fcmToken;
    this.validateSelf();
  }

}
