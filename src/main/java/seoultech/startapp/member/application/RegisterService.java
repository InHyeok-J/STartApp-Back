package seoultech.startapp.member.application;

import static com.slack.api.model.block.Blocks.section;
import com.slack.api.app_backend.interactive_components.payload.BlockActionPayload;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.composition.MarkdownTextObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seoultech.startapp.global.common.S3Uploader;
import seoultech.startapp.member.application.port.in.RegisterUseCase;
import seoultech.startapp.member.application.port.in.command.RegisterCommand;
import seoultech.startapp.member.application.port.out.DeleteMemberPort;
import seoultech.startapp.member.application.port.out.LoadMemberPort;
import seoultech.startapp.member.application.port.out.ResponseSlackHookDto;
import seoultech.startapp.member.application.port.out.SaveMemberPort;
import seoultech.startapp.member.application.port.out.SlackSenderPort;
import seoultech.startapp.member.application.port.out.SlackStudentCardDto;
import seoultech.startapp.member.domain.Member;
import seoultech.startapp.member.domain.MemberStatus;
import seoultech.startapp.member.exception.DuplicateStudentNoException;
import seoultech.startapp.member.exception.LeaveMemberException;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterService implements RegisterUseCase {

  private final LoadMemberPort loadMemberPort;
  private final SaveMemberPort saveMemberPort;
  private final PasswordEncoder passwordEncoder;
  private final SlackSenderPort slackSenderPort;
  private final String SLACK_CARD_APPROVE = "student_card_approve";
  private final S3Uploader s3Uploader;
  private final DeleteMemberPort deleteMemberPort;

  @Transactional
  @Override
  public void register(RegisterCommand command) {
    Member member = loadMemberPort.loadByStudentNoNullable(command.getStudentNo());

    if (member != null) {
      checkMemberStatus(member.getMemberStatus());
    }

    final String s3_CARD_DIRECTORY_PATH = "app/studentcard/";
    String cardImageUrl = s3Uploader.uploadFile(s3_CARD_DIRECTORY_PATH, command.getFile());
    Member preRegisterMember = MemberFactory.preRegisterMember(command,
        passwordEncoder.encode(command.getAppPassword()),
        cardImageUrl);

    Member savedMember = saveMemberPort.save(preRegisterMember);

    slackSenderPort.sendStudentCard(SlackStudentCardDto.toDto(savedMember));
  }

  private void checkMemberStatus(MemberStatus memberStatus) {
    switch (memberStatus) {
      case PRE_CARD_AUTH, POST_CARD_AUTH -> throw new DuplicateStudentNoException("학번이 중복됐습니다.");
      case LEAVE -> throw new LeaveMemberException("탈퇴된 회원 정보입니다.");
    }
  }


  @Transactional
  @Override
  public void studentCardSlackHook(BlockActionPayload payload) {
    payload.getMessage().getBlocks().remove(4);
    payload.getActions().forEach(action -> {
      Long memberId = Long.valueOf(action.getValue());
      Member member = loadMemberPort.loadByMemberId(memberId);
      if (action.getActionId().equals(SLACK_CARD_APPROVE)) {
        log.info("학생증 승인");
        member.cardApprove();
        saveMemberPort.save(member);
        payload.getMessage().getBlocks().add(approveBlock(payload.getUser().getName()));
      } else {
        log.info("학생증 거절");
        deleteMemberPort.deleteMember(member);
        payload.getMessage().getBlocks().add(rejectBlock(payload.getUser().getName()));
      }
      slackSenderPort.sendResponseSlackHook(
          new ResponseSlackHookDto(payload.getResponseUrl(), payload.getMessage().getBlocks()));
    });
  }

  private LayoutBlock approveBlock(String requestUser) {
    return section(section ->
        section.text(MarkdownTextObject.builder()
            .text(":white_check_mark: 학생증 인증이 *`승인`* 됐습니다. 확인 유저: @" + requestUser)
            .build()
        ));
  }

  private LayoutBlock rejectBlock(String requestUser) {
    return section(section ->
        section.text(MarkdownTextObject.builder()
            .text(":white_check_mark: 학생증 인증이 *`거절`* 됐습니다. 확인 유저: @" + requestUser)
            .build()
        ));
  }
}
