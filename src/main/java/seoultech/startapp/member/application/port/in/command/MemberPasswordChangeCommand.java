package seoultech.startapp.member.application.port.in.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import seoultech.startapp.global.common.SelfValidator;

@Getter
public class MemberPasswordChangeCommand extends SelfValidator<MemberPasswordChangeCommand> {

  @NotNull
  private Long memberId;

  @NotBlank
  @Pattern(regexp = "[a-zA-Z0-9~!@#$%^&*?<>,.+=]{8,16}")
  private String currentPassword;

  @NotBlank
  @Pattern(regexp = "[a-zA-Z0-9~!@#$%^&*?<>,.+=]{8,16}")
  private String newPassword;

  @Builder
  public MemberPasswordChangeCommand(Long memberId, String currentPassword, String newPassword) {
    this.memberId = memberId;
    this.currentPassword = currentPassword;
    this.newPassword = newPassword;
    validateSelf();
  }
}
