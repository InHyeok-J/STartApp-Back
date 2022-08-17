package seoultech.startapp.rent.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Renter {

    //멤버를 표현하는

    private Long renterId;

    private String studentNo;

    private String name;

    private String department;

    private String phoneNo;

    private String fcmToken;

    @Builder
    public Renter(Long renterId,
                  String studentNo,
                  String name,
                  String department,
                  String phoneNo,
                  String fcmToken) {
        this.renterId = renterId;
        this.studentNo = studentNo;
        this.name = name;
        this.department = department;
        this.phoneNo = phoneNo;
        this.fcmToken = fcmToken;
    }
}
