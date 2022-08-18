package seoultech.startapp.member.application.port.out;

import com.slack.api.model.block.LayoutBlock;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseSlackHookDto {

  private String responseUrl;
  private List<LayoutBlock> resultLayoutBlock;
}
