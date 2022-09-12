package seoultech.startapp.member.application.port.out;

public interface SmsPushPort {

  void pushSmsCode(String phoneNo, String randomString);
  void pushApprove(String phoeeNo);
  void pushReject(String phoneNo);
}
