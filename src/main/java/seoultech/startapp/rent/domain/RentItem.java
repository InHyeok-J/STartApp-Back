package seoultech.startapp.rent.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RentItem {

  private Rent rent;

  private Item item;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  @Builder
  public RentItem(Rent rent, Item item, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.rent = rent;
    this.item = item;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

}
