package seoultech.startapp.global.common;

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
import seoultech.startapp.global.property.NcpSmsProperty;

@Component
@RequiredArgsConstructor
@Slf4j
public class NcpApiAdapter {

  private final RestTemplate restTemplate;
  private final NcpSmsProperty smsProperty;


  public void pushAlimTalk(JsonObject body){
    final String NPC_HOST = "https://sens.apigw.ntruss.com";
    final String NPC_URL = "/alimtalk/v2/services/" + smsProperty.getServiceId() + "/messages";
    final String REQUEST_URL = NPC_HOST + NPC_URL;
    try {
      String timeStamp = Long.toString(System.currentTimeMillis());
      HttpHeaders headers = new HttpHeaders();
      headers.set("Content-Type", "application/json; charset=utf-8");
      headers.set("x-ncp-apigw-timestamp", timeStamp);
      headers.set("x-ncp-iam-access-key", smsProperty.getAccessKey());
      headers.set("x-ncp-apigw-signature-v2", makeSignature(NPC_URL, timeStamp));

      HttpEntity<String> httpEntity = new HttpEntity<>(
          body.toString(), headers);

      ResponseEntity<String> response = restTemplate.postForEntity(REQUEST_URL,
          httpEntity, String.class);
      System.out.println(response);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private String makeSignature(String url, String timeStamp)
      throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
    String space = " ";
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
