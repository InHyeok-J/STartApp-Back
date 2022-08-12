package seoultech.startapp.rent.adapter.out;

import org.springframework.stereotype.Component;
import seoultech.startapp.rent.domain.Rent;

@Component
class RentMapper {

    public Rent mapToDomainRent(JpaRent jpaRent){
        return Rent.builder()
            .memberId(jpaRent.getMemberId())
            .account(jpaRent.getAccount())
            .purpose(jpaRent.getPurpose())
            .rentStatus(jpaRent.getRentStatus())
            .itemCategory(jpaRent.getItemCategory())
            .startTime(jpaRent.getStartTime())
            .endTime(jpaRent.getEndTime())
            .build();

    }

    public JpaRent mapToJpaRent(Rent rent){
        return JpaRent.builder()
            .id(rent.getRentId() == null ? null : rent.getRentId())
            .memberId(rent.getMemberId())
            .account(rent.getAccount())
            .purpose(rent.getPurpose())
            .startTime(rent.getStartTime())
            .endTime(rent.getEndTime())
            .rentStatus(rent.getRentStatus())
            .itemCategory(rent.getItemCategory())
            .build();
    }

}
