package seoultech.startapp.rent.adapter.out;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
class JpaRentItemId implements Serializable {

    private Long jpaRent;

    private Long jpaItem;
}
