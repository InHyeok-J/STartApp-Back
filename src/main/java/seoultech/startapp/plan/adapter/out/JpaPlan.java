package seoultech.startapp.plan.adapter.out;

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
@Entity(name = "plan")
@NoArgsConstructor
class JpaPlan {

    @Id
    @Column(name = "plan_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(nullable = false,name = "plan_name")
    private String planName;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false,name = "start_time")
    private LocalDateTime startTime;

    @Column(nullable = false,name = "end_time")
    private LocalDateTime endTime;

    @Builder
    public JpaPlan(Long id, String planName, String color, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.planName = planName;
        this.color = color;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
