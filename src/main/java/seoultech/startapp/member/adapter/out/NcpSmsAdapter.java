package seoultech.startapp.member.adapter.out;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import seoultech.startapp.global.exception.BusinessException;
import seoultech.startapp.global.exception.ErrorType;
import seoultech.startapp.global.property.NcpSmsProperty;
import seoultech.startapp.member.application.port.out.SmsPushPort;

@Component
@RequiredArgsConstructor
@Slf4j
public class NcpSmsAdapter implements SmsPushPort {

  private final RestTemplate restTemplate;
  private final NcpSmsProperty smsProperty;

  @Override
  public void push(String phoneNo, String randomString) {
    final String NPC_HOST = "https://sens.apigw.ntruss.com";
    final String NPC_URL = "/sms/v2/services/" + smsProperty.getServiceId() + "/messages";
    final String REQUEST_URL = NPC_HOST + NPC_URL;
    try {
      String timeStamp = Long.toString(System.currentTimeMillis());
      HttpHeaders headers = new HttpHeaders();
      headers.set("Content-Type", "application/json; charset=utf-8");
      headers.set("x-ncp-apigw-timestamp", timeStamp);
      headers.set("x-ncp-iam-access-key", smsProperty.getAccessKey());
      headers.set("x-ncp-apigw-signature-v2", makeSignature(NPC_URL, timeStamp));

      JsonObject body = new JsonObject();
      JsonArray messagesArray = new JsonArray();
      JsonObject messages = new JsonObject();

      String replaceNo = phoneNo.replaceAll("[^0-9]", "");
      messages.addProperty("to", replaceNo);
      messagesArray.add(messages);

      body.addProperty("type", "SMS");
      body.addProperty("from", smsProperty.getSenderNo());
      body.addProperty("content",
          "[서울과학기술대학교 총학생회] 인증번호는 " + randomString + "" + " 입니다. 정확히 입력해 주세요.");
      body.add("messages", messagesArray);

      HttpEntity<String> httpEntity = new HttpEntity<>(
          body.toString(), headers);

      ResponseEntity<String> response = restTemplate.postForEntity(REQUEST_URL,
          httpEntity, String.class);
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException(ErrorType.INTERNAL_SERVER_ERROR, "SMS PUSH 실패");
    }
  }

  public String makeSignature(String url, String timeStamp)
      throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
    String space = " ";          // one space
    String newLine = "\n";          // new line
    String method = "POST";          // method
    String accessKey = smsProperty.getAccessKey();      // access key id (from portal or Sub Account)
    String secretKey = smsProperty.getSecretKey();

    String message = new StringBuilder()
        .append(method)
        .append(space)
        .append(url)
        .append(newLine)
        .append(timeStamp)
        .append(newLine)
        .append(accessKey)
        .toString();

    SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8),
        "HmacSHA256");
    Mac mac = Mac.getInstance("HmacSHA256");
    mac.init(signingKey);

    byte[] rawHmac = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));

    return Base64.encodeBase64String(rawHmac);
  }

}
