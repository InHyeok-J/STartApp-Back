package seoultech.startapp.member.application.port.in;

public interface MemberShipUseCase {

  void addMemberShip(String studentNo);
  boolean existCheckMemberShip(String studentNo);

}
