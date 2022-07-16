package seoultech.startapp.member.adapter.in;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequest {

  private final String studentNo;
  private final String password;
}
