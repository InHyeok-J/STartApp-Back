package seoultech.startapp.rent.adapter.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import seoultech.startapp.global.common.BaseTimeJpaEntity;
import seoultech.startapp.rent.domain.RentItemStatus;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor
@Entity(name = "rent_item")
@IdClass(JpaRentItemId.class)
public class JpaRentItem extends BaseTimeJpaEntity {

    @Id
    @JoinColumn(name = "rent_id")
    @ManyToOne(fetch = LAZY)
    private JpaRent jpaRent;

    @Id
    @JoinColumn(name = "item_id")
    @ManyToOne(fetch = LAZY)
    private JpaItem jpaItem;

    @Enumerated(STRING)
    private RentItemStatus rentItemStatus;

//    public void addJpaRent(JpaRent jpaRent){
//        this.jpaRent = jpaRent;
//        jpaRent.getRentItems().add(this);
//    }
    @Builder
    public JpaRentItem(JpaRent jpaRent, JpaItem jpaItem, RentItemStatus rentItemStatus) {
        this.jpaRent = jpaRent;
        this.jpaItem = jpaItem;
        this.rentItemStatus = rentItemStatus;
    }
}
