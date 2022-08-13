package seoultech.startapp.member.application.port.in.command;

import java.util.HashSet;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import seoultech.startapp.global.common.SelfValidator;
import seoultech.startapp.member.domain.MemberProfile;
import seoultech.startapp.member.domain.StudentStatus;

@Getter
@EqualsAndHashCode(callSuper = false)
public class UpdateMemberCommand extends SelfValidator<UpdateMemberCommand> {

  @NotNull
  private Long memberId;

  @NotBlank
  private String name;

  @NotBlank
  private String department;

  @NotBlank
  @Email
  private String email;

  @NotBlank
  private String phoneNo;

  @NotNull
  private StudentStatus studentStatus;

  @NotNull
  private Boolean memberShip;

  @Builder
  public UpdateMemberCommand(Long memberId, String name, String department, String email,
      String phoneNo, String studentStatus, Boolean memberShip) {
    this.memberId = memberId;
    this.name = name;
    this.department = department;
    this.email = email;
    this.phoneNo = phoneNo;
    this.studentStatus = studentStatusValidate(studentStatus);
    this.memberShip = memberShip;
    this.validateSelf();
  }

  private StudentStatus studentStatusValidate(String status){
    try{
      return StudentStatus.valueOf(status);
    }catch (Exception e){
      throw new ConstraintViolationException("validation fail", new HashSet<>());
    }
  }

  public MemberProfile toProfile(String studentNo){
    return MemberProfile.builder()
        .studentNo(studentNo)
        .name(name)
        .department(department)
        .phoneNo(phoneNo)
        .email(email)
        .studentStatus(studentStatus)
        .memberShip(memberShip)
        .build();
  }
}
