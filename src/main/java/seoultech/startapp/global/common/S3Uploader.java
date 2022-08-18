package seoultech.startapp.global.common;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import seoultech.startapp.global.exception.BusinessException;
import seoultech.startapp.global.exception.ErrorType;
import seoultech.startapp.global.property.AwsS3Property;

@Slf4j
@Component
@RequiredArgsConstructor
public class S3Uploader {

  private final AmazonS3 amazonS3;
  private final AwsS3Property property;
  private final String AWSURL = "https://startappbucket.s3.ap-northeast-2.amazonaws.com/";

  public String uploadFile(String directoryPath, MultipartFile multipartFile) {

    String fileName = createFileName( directoryPath, multipartFile.getOriginalFilename());
    ObjectMetadata objectMetadata = new ObjectMetadata();
    objectMetadata.setContentLength(multipartFile.getSize());
    objectMetadata.setContentType(multipartFile.getContentType());
    try {
      InputStream inputStream = multipartFile.getInputStream();
      amazonS3.putObject(
          new PutObjectRequest(property.getBucket(), fileName, inputStream, objectMetadata));
      return AWSURL + fileName;
    } catch (Exception e) {
      throw new BusinessException(ErrorType.INTERNAL_SERVER_ERROR, "이미지 업로드 실패");
    }
  }

  private String createFileName(String directoryPath, String fimeName) {
    return directoryPath+ UUID.randomUUID().toString().concat(fimeName);
  }
}
