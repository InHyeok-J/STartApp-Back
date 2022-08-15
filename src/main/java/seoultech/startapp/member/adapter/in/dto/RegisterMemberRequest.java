package seoultech.startapp.member.adapter.in.dto;

import seoultech.startapp.member.domain.StudentStatus;

public record RegisterMemberRequest(String studentNo, String appPassword, String name,
                                    String department, String phoneNo, String fcmToken,
                                    String studentStatus, String email) {

}
