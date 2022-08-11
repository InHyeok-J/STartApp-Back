package seoultech.startapp.rent.adapter.out;

import lombok.Getter;
import lombok.NoArgsConstructor;
import seoultech.startapp.rent.domain.RentStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor
@Entity(name = "rent_item")
class JpaRentItem {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "rent_item_id")
    private Long id;

   // @Id
    @JoinColumn(name = "rent_id")
    @ManyToOne(fetch = LAZY)
    private JpaRent rentId;

    //@Id
    @JoinColumn(name = "item_id")
    @ManyToOne(fetch = LAZY)
    private JpaItem itemId;

    @Enumerated(STRING)
    private RentStatus rentStatus;

}
