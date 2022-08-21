package seoultech.startapp.rent.application;

import lombok.Builder;
import lombok.Getter;
import seoultech.startapp.rent.domain.Renter;

@Getter
public class RenterResponse {

  private Long renterId;

  private String studentNo;

  private String name;

  private String department;

  private String phoneNo;

  @Builder
  public RenterResponse(Long renterId, String studentNo, String name, String department,
      String phoneNo) {
    this.renterId = renterId;
    this.studentNo = studentNo;
    this.name = name;
    this.department = department;
    this.phoneNo = phoneNo;
  }

  public static RenterResponse toDto(Renter renter){
    return RenterResponse.builder()
        .renterId(renter.getRenterId())
        .studentNo(renter.getStudentNo())
        .name(renter.getName())
        .department(renter.getDepartment())
        .phoneNo(renter.getPhoneNo() == null ? "" : renter.getPhoneNo())
        .build();
  }
}
