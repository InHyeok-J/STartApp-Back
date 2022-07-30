package seoultech.startapp.event.application.port.in;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
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
}
