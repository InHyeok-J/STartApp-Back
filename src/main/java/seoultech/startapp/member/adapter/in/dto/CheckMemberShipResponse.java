package seoultech.startapp.member.adapter.in.dto;

import lombok.Getter;

@Getter
public class CheckMemberShipResponse {

  private Boolean isExist;

  public CheckMemberShipResponse(Boolean isExist) {
    this.isExist = isExist;
  }
}
