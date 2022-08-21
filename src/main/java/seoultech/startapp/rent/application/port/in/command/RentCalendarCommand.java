package seoultech.startapp.rent.application.port.in.command;

import lombok.Builder;
import lombok.Getter;
import seoultech.startapp.global.common.SelfValidator;
import seoultech.startapp.rent.domain.ItemCategory;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;

@Getter
public class RentCalendarCommand extends SelfValidator<RentCalendarCommand> {

  @NotNull
  @Min(2021)
  @Max(2999)
  private int year;

  @NotNull
  @Min(1)
  @Max(12)
  private int month;

  @NotNull
  private ItemCategory itemCategory;

  @Builder
  public RentCalendarCommand(int year, int month,
      String itemCategory) {
    this.year = year;
    this.month = month;
    this.itemCategory = validationCategory(itemCategory);
  }

  private ItemCategory validationCategory(String category){
    try{
      return ItemCategory.valueOf(category);
    }catch (Exception e){
      throw new ConstraintViolationException(new HashSet<>());
    }
  }
}
