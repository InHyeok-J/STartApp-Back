package seoultech.startapp.member.adapter.out;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import seoultech.startapp.global.common.NcpApiAdapter;
import seoultech.startapp.member.application.port.out.SmsPushPort;

@Component
@RequiredArgsConstructor
@Slf4j
public class NcpSmsAdapter implements SmsPushPort {

  private final NcpApiAdapter ncpApiAdapter;
  private final String KAKAO_CHANNEL_NAME = "@서울과학기술대학교_총학생회";

  @Override
  public void pushSmsCode(String phoneNo, String randomString) {
    //init
    JsonObject body = new JsonObject();
    JsonArray messagesArray = new JsonArray();
    JsonObject messageDetail = new JsonObject();

    // phone No remove "-"
    String replacePhoneNo = phoneNo.replaceAll("[^0-9]", "");

    // setting id and code
    body.addProperty("plusFriendId", KAKAO_CHANNEL_NAME);
    body.addProperty("templateCode", "stAuthCode");

    // to => receiver
    messageDetail.addProperty("to", replacePhoneNo);
    // content = template content
    messageDetail.addProperty("content",
        "[서울과학기술대학교 총학생회]인증번호는 " + randomString + " 입니다. 정확히 입력해주세요.");
    messagesArray.add(messageDetail);

    body.add("messages", messagesArray);

    ncpApiAdapter.pushAlimTalk(body);
  }

  @Override
  public void pushApprove(String phoneNo) {
    //init
    JsonObject body = new JsonObject();
    JsonArray messagesArray = new JsonArray();
    JsonObject messageDetail = new JsonObject();

    // phone No remove "-"
    String replacePhoneNo = phoneNo.replaceAll("[^0-9]", "");

    // setting id and code
    body.addProperty("plusFriendId", KAKAO_CHANNEL_NAME);
    body.addProperty("templateCode", "stApprove");

    // to => receiver
    messageDetail.addProperty("to", replacePhoneNo);
    // content = template content
    messageDetail.addProperty("content",
        "[서울과학기술대학교 총학생회] 학생증 본인인증심사가 승인되었습니다.");
    messagesArray.add(messageDetail);

    body.add("messages", messagesArray);
    ncpApiAdapter.pushAlimTalk(body);
  }

  @Override
  public void pushReject(String phoneNo) {
    //init
    JsonObject body = new JsonObject();
    JsonArray messagesArray = new JsonArray();
    JsonObject messageDetail = new JsonObject();

    // phone No remove "-"
    String replacePhoneNo = phoneNo.replaceAll("[^0-9]", "");

    // setting id and code
    body.addProperty("plusFriendId", KAKAO_CHANNEL_NAME);
    body.addProperty("templateCode", "stReject");

    // to => receiver
    messageDetail.addProperty("to", replacePhoneNo);
    // content = template content
    messageDetail.addProperty("content",
        "[서울과학기술대학교 총학생회] 학생증 본인인증심사가 거절되었습니다. "
            + "입력한 정보와 학생증의 정보가 일치한지 확인해주세요.");
    messagesArray.add(messageDetail);

    body.add("messages", messagesArray);
    ncpApiAdapter.pushAlimTalk(body);
  }


}
