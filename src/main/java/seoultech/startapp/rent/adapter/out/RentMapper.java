package seoultech.startapp.rent.adapter.out;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import seoultech.startapp.rent.domain.Rent;
import seoultech.startapp.rent.domain.Renter;

@Component
@RequiredArgsConstructor
class RentMapper {

    public Rent mapToDomainRent(JpaRent jpaRent) {
        return Rent.builder()
                   .rentId(jpaRent.getId())
                   .renter(Renter.builder().renterId(jpaRent.getMemberId()).build())
                   .account(jpaRent.getAccount())
                   .purpose(jpaRent.getPurpose())
                   .rentStatus(jpaRent.getRentStatus())
                   .itemCategory(jpaRent.getItemCategory())
                   .startTime(jpaRent.getStartTime())
                   .endTime(jpaRent.getEndTime())
                   .createAt(jpaRent.getCreatedAt())
                   .updateAt(jpaRent.getUpdatedAt())
                   .build();
    }


    public JpaRent mapToJpaRent(Rent rent){
        return JpaRent.builder()
                      .id(rent.getRentId() == null ? null : rent.getRentId())
                      .memberId(rent.getRenter().getRenterId())
                      .account(rent.getAccount())
                      .purpose(rent.getPurpose())
                      .startTime(rent.getStartTime())
                      .endTime(rent.getEndTime())
                      .rentStatus(rent.getRentStatus())
                      .itemCategory(rent.getItemCategory())
                      .createAt(rent.getCreateAt())
                      .updateAt(rent.getUpdateAt())
                      .build();
    }

}

