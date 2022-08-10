package seoultech.startapp.rent.adapter.out;

import lombok.Getter;
import lombok.NoArgsConstructor;
import seoultech.startapp.rent.domain.ItemCategory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity(name = "rent")
@Getter
@NoArgsConstructor
class JpaRent {

    @Id
    @Column(name = "rent_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Enumerated(EnumType.STRING)
    @Column(name = "rent_status",nullable = false)
    private RentStatus rentStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_category",nullable = false)
    private ItemCategory itemCategory;

    @Column(name = "start_time",nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time",nullable = false)
    private LocalDateTime endTime;


}
