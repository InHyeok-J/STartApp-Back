package seoultech.startapp.member.application.port.out;

public interface LoadMemberShipPort {

    boolean existByStudentNo(String studentNo);
}
