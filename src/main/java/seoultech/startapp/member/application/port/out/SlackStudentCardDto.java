package seoultech.startapp.member.application.port.out;

import lombok.Builder;
import lombok.Getter;
import seoultech.startapp.member.domain.Member;

@Getter
public class SlackStudentCardDto {
  private Long memberId;
  private String studentNo;
  private String department;
  private String name;
  private String studentCardImage;

  @Builder
  public SlackStudentCardDto(String studentNo, String department, String name,
      String studentCardImage, Long memberId) {
    this.studentNo = studentNo;
    this.department = department;
    this.name = name;
    this.studentCardImage = studentCardImage;
    this.memberId = memberId;
  }

  public static SlackStudentCardDto toDto(Member member){
    return SlackStudentCardDto.builder()
        .studentCardImage(member.getStudentCardImage())
        .studentNo(member.getProfile().getStudentNo())
        .department(member.getProfile().getDepartment())
        .name(member.getProfile().getName())
        .memberId(member.getMemberId())
        .build();
  }

  @Override
  public String toString() {
    return "SlackStudentCardDto{" +
        "memberId=" + memberId +
        ", studentNo='" + studentNo + '\'' +
        ", department='" + department + '\'' +
        ", name='" + name + '\'' +
        ", studentCardImage='" + studentCardImage + '\'' +
        '}';
  }
}
