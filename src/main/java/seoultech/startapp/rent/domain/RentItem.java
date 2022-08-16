package seoultech.startapp.rent.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RentItem {

    private Rent rent;

    private Item item;

    private RentItemStatus rentItemStatus;

    @Builder
    public RentItem(Rent rent, Item item, RentItemStatus rentItemStatus) {
        this.rent = rent;
        this.item = item;
        this.rentItemStatus = rentItemStatus;
    }

    public void changeRentItemStatus(RentStatus rentStatus){
        this.rentItemStatus = RentItemStatus.valueOf(String.valueOf(rentStatus));
    }
}
