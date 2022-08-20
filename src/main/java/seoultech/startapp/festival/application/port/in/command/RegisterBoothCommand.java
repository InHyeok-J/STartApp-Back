package seoultech.startapp.festival.application.port.in.command;

import javax.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import seoultech.startapp.festival.domain.Booth;
import seoultech.startapp.global.common.SelfValidator;

@Getter
@EqualsAndHashCode(callSuper = false)
public class RegisterBoothCommand extends SelfValidator<RegisterBoothCommand> {

  @NotBlank
  private String name;

  public RegisterBoothCommand(String name) {
    this.name = name;
    validateSelf();
  }

  public Booth toDomainBooth(){
    return Booth.builder()
        .name(this.name)
        .congestion(1)
        .build();
  }
}
