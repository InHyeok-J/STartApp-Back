package seoultech.startapp.member.adapter.out;

import static com.slack.api.model.block.Blocks.actions;
import static com.slack.api.model.block.Blocks.divider;
import static com.slack.api.model.block.Blocks.image;
import static com.slack.api.model.block.Blocks.section;
import static com.slack.api.model.block.composition.BlockCompositions.markdownText;
import static com.slack.api.model.block.composition.BlockCompositions.plainText;
import static com.slack.api.model.block.element.BlockElements.asElements;
import static com.slack.api.model.block.element.BlockElements.button;
import static com.slack.api.webhook.WebhookPayloads.payload;

import com.slack.api.Slack;
import com.slack.api.app_backend.interactive_components.ActionResponseSender;
import com.slack.api.app_backend.interactive_components.payload.BlockActionPayload;
import com.slack.api.app_backend.interactive_components.response.ActionResponse;
import com.slack.api.model.block.ImageBlock;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.composition.PlainTextObject;
import com.slack.api.webhook.WebhookResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import seoultech.startapp.global.exception.BusinessException;
import seoultech.startapp.global.exception.ErrorType;
import seoultech.startapp.global.property.SlackProperty;
import seoultech.startapp.member.application.port.out.ResponseSlackHookDto;
import seoultech.startapp.member.application.port.out.SlackSenderPort;
import seoultech.startapp.member.application.port.out.SlackStudentCardDto;

@Slf4j
@Component
@RequiredArgsConstructor
public class SlackAdapter implements SlackSenderPort {

  private final SlackProperty slackProperty;

  @Override
  public void sendStudentCard(SlackStudentCardDto dto) {
    try {
      Slack.getInstance().send(slackProperty.getWebhookUrl(), payload(p-> p
              .text("학생증 인증 요청")
              .blocks(createBlock(dto))
          ));
    } catch (Exception e) {
      e.printStackTrace();
      throw new BusinessException(ErrorType.INTERNAL_SERVER_ERROR, "슬랙 전송 실패");
    }
  }

  @Override
  public void sendResponseSlackHook(ResponseSlackHookDto dto) {
    List<LayoutBlock> resultLayoutBlock = dto.getResultLayoutBlock();
    LayoutBlock replacedBlock = replaceImageBlock((ImageBlock) resultLayoutBlock.get(3));
    resultLayoutBlock.remove(3);
    resultLayoutBlock.add(replacedBlock);

    ActionResponseSender sender = new ActionResponseSender(Slack.getInstance());
    ActionResponse response = ActionResponse.builder()
        .replaceOriginal(true)
        .text("학생증 인증 확인")
        .blocks(resultLayoutBlock)
        .build();
    try {
      WebhookResponse send = sender.send(dto.getResponseUrl(), response);
      log.info(send.getBody());
    }catch (Exception e){
      e.printStackTrace();;
    }
  }

  private LayoutBlock replaceImageBlock(ImageBlock layoutBlock){
    return image(
        image -> image.title(PlainTextObject.builder().text("학생증 사진").build())
            .imageUrl(layoutBlock.getImageUrl())
            .altText("학생증 사진")
    );
  }

  private List<LayoutBlock> createBlock(SlackStudentCardDto dto) {
    List<LayoutBlock> layoutBlocks = new ArrayList<>();
    layoutBlocks.add(section(section -> section.text(markdownText(":star: *신규 학생증 등록 요청입니다.* :star:"))));
    layoutBlocks.add(divider());
    layoutBlocks.add(section(
        section -> section.text(markdownText("*[요청 정보]* \n" + "```\n" + makeBody(dto) + "```"))));
    layoutBlocks.add(image(
        image -> image.title(PlainTextObject.builder().text("학생증 사진").build())
            .imageUrl(dto.getStudentCardImage())
            .altText("학생증 사진")
    ));
    layoutBlocks.add(actions(actions ->
        actions.elements((asElements(
            button(b -> b.text(plainText(pt -> pt.emoji(true).text("승인")))
                .value(dto.getMemberId().toString())
                .style("primary")
                .actionId("student_card_approve")
            ),
            button(b -> b.text(plainText(pt -> pt.emoji(true).text("거절")))
                .value(dto.getMemberId().toString())
                .style("danger")
                .actionId("student_card_reject")
            )
        )))
      )
    );
    return layoutBlocks;
  }

  private String makeBody(SlackStudentCardDto dto) {
    StringBuilder sb = new StringBuilder();
    sb.append("아이디 : ").append(dto.getMemberId().toString()).append("\n");
    sb.append("학번 : ").append(dto.getStudentNo()).append("\n");
    sb.append("이름 : ").append(dto.getName()).append("\n");
    sb.append("학과 : ").append(dto.getDepartment()).append("\n");
    return sb.toString();
  }
}
