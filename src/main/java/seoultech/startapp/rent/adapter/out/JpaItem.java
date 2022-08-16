package seoultech.startapp.rent.adapter.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import seoultech.startapp.global.common.BaseTimeJpaEntity;
import seoultech.startapp.global.converter.BooleanToYNConverter;
import seoultech.startapp.rent.domain.ItemCategory;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.time.LocalDateTime;

import static java.lang.Boolean.FALSE;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Entity(name = "item")
@NoArgsConstructor
class JpaItem extends BaseTimeJpaEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Enumerated(STRING)
    @Column(name = "item_category",nullable = false)
    private ItemCategory itemCategory;

    @Column(name = "item_no",nullable = false, unique = true)
    private String itemNo;

    @Column(name = "is_available")
    @Convert(converter = BooleanToYNConverter.class)
    private Boolean isAvailable;

    @Column(name = "is_rentable")
    @Convert(converter = BooleanToYNConverter.class)
    private Boolean isRentable = FALSE;

    @Builder
    public JpaItem(Long id, ItemCategory itemCategory, String itemNo, Boolean isAvailable, Boolean isRentable, LocalDateTime createAt,LocalDateTime updateAt) {
        this.id = id;
        this.itemCategory = itemCategory;
        this.itemNo = itemNo;
        this.isAvailable = isAvailable;
        this.isRentable = isRentable;
        this.createdAt = createAt;
        this.updatedAt = updateAt;
    }
}
