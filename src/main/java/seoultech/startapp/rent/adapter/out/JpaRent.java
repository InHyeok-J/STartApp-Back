package seoultech.startapp.rent.adapter.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import seoultech.startapp.global.common.BaseTimeJpaEntity;
import seoultech.startapp.rent.domain.ItemCategory;
import seoultech.startapp.rent.domain.RentStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.EnumType.STRING;

@Entity(name = "rent")
@Getter
@NoArgsConstructor
public class JpaRent extends BaseTimeJpaEntity {

    @Id
    @Column(name = "rent_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Column(nullable = false)
    private int account;

    @Column(nullable = false)
    private String purpose;

    @Column(name = "start_time",nullable = false)
    private LocalDate startTime;

    @Column(name = "end_time",nullable = false)
    private LocalDate endTime;

    @Enumerated(STRING)
    @Column(name = "rent_status",nullable = false)
    private RentStatus rentStatus = RentStatus.WAIT;

    @Enumerated(STRING)
    @Column(name = "item_category",nullable = false)
    private ItemCategory itemCategory;

    @OneToMany(mappedBy = "jpaRent")
    private List<JpaRentItem> rentItems = new ArrayList<>();

    @Builder
    public JpaRent(Long id,
                   Long memberId,
                   int account,
                   String purpose,
                   LocalDate startTime,
                   LocalDate endTime,
                   RentStatus rentStatus,
                   ItemCategory itemCategory,
                   List<JpaRentItem> rentItems,
                   LocalDateTime createAt,
                   LocalDateTime updateAt) {
        this.id = id;
        this.memberId = memberId;
        this.account = account;
        this.purpose = purpose;
        this.startTime = startTime;
        this.endTime = endTime;
        this.rentStatus = rentStatus;
        this.itemCategory = itemCategory;
        this.rentItems = rentItems;
        this.createdAt = createAt;
        this.updatedAt = updateAt;
    }
}
