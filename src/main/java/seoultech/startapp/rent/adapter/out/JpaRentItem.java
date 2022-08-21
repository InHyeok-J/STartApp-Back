package seoultech.startapp.rent.adapter.out;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import seoultech.startapp.global.common.BaseTimeJpaEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
  @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
  private JpaItem jpaItem;


  @Builder
  public JpaRentItem(JpaRent jpaRent, JpaItem jpaItem,
      LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.jpaRent = jpaRent;
    this.jpaItem = jpaItem;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

}
