package seoultech.startapp.member.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemberShipRepository extends JpaRepository<JpaMemberShip,Long> {

    Boolean existsByStudentNo(String studentNo);
}
