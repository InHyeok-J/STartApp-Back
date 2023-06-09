package seoultech.startapp.event.adapter.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Entity(name = "event")
@NoArgsConstructor
class JpaEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    @Size(max = 255)
    @Column(nullable = false)
    private String title;

    @Column(name = "form_link",nullable = false)
    private String formLink;

    @Column(name = "image_url",nullable = false)
    private String imageUrl;

    @Column(name = "start_time",nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time",nullable = false)
    private LocalDateTime endTime;


    @Builder
    public JpaEvent(Long id, String title, String formLink, String imageUrl, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.title = title;
        this.formLink = formLink;
        this.imageUrl = imageUrl;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
