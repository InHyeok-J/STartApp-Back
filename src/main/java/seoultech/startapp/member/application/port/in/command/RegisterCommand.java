package seoultech.startapp.member.application.port.in.command;

import java.util.HashSet;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import seoultech.startapp.global.common.SelfValidator;
import seoultech.startapp.member.adapter.in.RegisterMemberRequest;
import seoultech.startapp.member.domain.StudentStatus;

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

  @NotNull
  private final StudentStatus studentStatus;

  @NotBlank
  @Email
  private final String email;

  public RegisterCommand(String studentNo, String appPassword, String name,
      String department, String phoneNo, String fcmToken, String studentStatus, String email) {
    this.StudentNo = studentNo;
    this.appPassword = appPassword;
    this.name = name;
    this.department = department;
    this.phoneNo = phoneNo;
    this.fcmToken = fcmToken;
    this.studentStatus = studentStatusValidate(studentStatus);
    this.email = email;
    this.validateSelf();
  }

  public RegisterCommand(RegisterMemberRequest request){
    System.out.println("tesr");
    this.StudentNo = request.studentNo();
    this.appPassword = request.appPassword();
    this.name = request.name();
    this.department = request.department();
    this.phoneNo = request.phoneNo();
    this.fcmToken = request.fcmToken();
    this.studentStatus = studentStatusValidate(request.studentStatus());
    this.email = request.email();
    this.validateSelf();
  }

  private StudentStatus studentStatusValidate(String status){
    try{
      return StudentStatus.valueOf(status);
    }catch (Exception e){
      throw new ConstraintViolationException("validation fail", new HashSet<>());
    }
  }

}
