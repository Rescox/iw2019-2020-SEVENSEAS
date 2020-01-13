package es.uca.iw.sss.spring;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventReservationRepository extends JpaRepository<EventReservation, Long> {
    Reservation findById(int id);

}
