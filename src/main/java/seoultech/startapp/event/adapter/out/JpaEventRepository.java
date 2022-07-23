package seoultech.startapp.event.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface JpaEventRepository extends JpaRepository<JpaEvent,Long> {

    Optional<JpaEvent> findById(Long eventId);
}
