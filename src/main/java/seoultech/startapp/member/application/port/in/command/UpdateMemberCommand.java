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
  private String phoneNo;


  @NotNull
  private Boolean memberShip;

  @Builder
  public UpdateMemberCommand(Long memberId, String name, String department,
      String phoneNo,  Boolean memberShip) {
    this.memberId = memberId;
    this.name = name;
    this.department = department;
    this.phoneNo = phoneNo;
    this.memberShip = memberShip;
    this.validateSelf();
  }

  public MemberProfile toProfile(String studentNo){
    return MemberProfile.builder()
        .studentNo(studentNo)
        .name(name)
        .department(department)
        .phoneNo(phoneNo)
        .memberShip(memberShip)
        .build();
  }
}
