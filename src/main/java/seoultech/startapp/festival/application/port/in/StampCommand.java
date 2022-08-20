package seoultech.startapp.festival.application.port.in;

import java.util.HashSet;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import seoultech.startapp.festival.domain.StampList;
import seoultech.startapp.global.common.SelfValidator;

@Getter
public class StampCommand extends SelfValidator<StampCommand> {


  @NotNull
  private Long memberId;

  @NotNull
  private StampList target;


  @Builder
  public StampCommand(Long memberId, String target) {
    this.memberId = memberId;
    this.target = validationStamp(target);
    validateSelf();
  }

  private StampList validationStamp(String target){
    String upperCaseTarget = target.toUpperCase();
    try {
      return StampList.valueOf(upperCaseTarget);
    }catch (Exception e){
      throw new ConstraintViolationException(new HashSet<>());
    }
  }

}
