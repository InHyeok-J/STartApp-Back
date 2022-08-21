package seoultech.startapp.rent.adapter.out;

import org.springframework.stereotype.Component;
import seoultech.startapp.member.adapter.out.JpaMember;
import seoultech.startapp.rent.domain.Renter;

@Component
public class RenterMapper {

  public Renter toRenter(JpaMember jpaMember){
    return Renter.builder()
        .department(jpaMember.getDepartment())
        .renterId(jpaMember.getId())
        .fcmToken(jpaMember.getFcmToken())
        .name(jpaMember.getName())
        .studentNo(jpaMember.getStudentNo())
        .phoneNo(jpaMember.getPhoneNo())
        .build();
  }

}
