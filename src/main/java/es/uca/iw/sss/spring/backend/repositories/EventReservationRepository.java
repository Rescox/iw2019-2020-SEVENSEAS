package es.uca.iw.sss.spring;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventReservationRepository extends JpaRepository<EventReservation, Long> {
    EventReservation findById(int id);
    List<EventReservation> findByEvent(Event event);
}
