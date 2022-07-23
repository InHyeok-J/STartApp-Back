package seoultech.startapp.global.config.web;


import lombok.AllArgsConstructor;
import lombok.Getter;
import seoultech.startapp.member.domain.MemberRole;

@Getter
@AllArgsConstructor
public class AuthUser {

  private Long id;

  private MemberRole role;
}
