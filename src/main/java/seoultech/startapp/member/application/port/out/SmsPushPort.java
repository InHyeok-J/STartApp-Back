package seoultech.startapp.member.application.port.out;

public interface SmsPushPort {

  void push(String phoneNo, String randomString);
}
