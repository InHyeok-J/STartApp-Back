package seoultech.startapp.member.adapter.out;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity(name = "memberShip")
@NoArgsConstructor
public class JpaMemberShip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberShip_id")
    private Long id;

    @Column(name = "student_no", unique = true, nullable = false)
    private String studentNo;

}
