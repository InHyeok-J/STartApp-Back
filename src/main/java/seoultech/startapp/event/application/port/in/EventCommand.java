package seoultech.startapp.event.application.port.in;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import seoultech.startapp.event.domain.Event;
import seoultech.startapp.global.common.SelfValidator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventCommand extends SelfValidator<EventCommand> {

    @Size(max = 255)
    @NotBlank
    private String title;
    @NotBlank
    private String formLink;
    @NotBlank
    private String imageUrl;
    @NotNull
    private LocalDateTime startTime;

    @NotNull
    private LocalDateTime endTime;

    @Builder
    public EventCommand(String title, String formLink, String imageUrl, LocalDateTime startTime, LocalDateTime endTime) {
        this.title = title;
        this.formLink = formLink;
        this.imageUrl = imageUrl;
        this.startTime = startTime;
        this.endTime = endTime;
        this.validateSelf();
    }

    public Event ToDomainEvent(){
        return Event.builder()
                    .title(this.title)
                    .imageUrl(this.imageUrl)
                    .formLink(this.formLink)
                    .startTime(this.startTime)
                    .endTime(this.endTime)
                    .build();
    }
}
