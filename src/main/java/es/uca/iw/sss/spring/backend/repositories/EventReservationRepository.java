package es.uca.iw.sss.spring.backend.repositories;

import es.uca.iw.sss.spring.backend.entities.EventReservation;
import es.uca.iw.sss.spring.backend.entities.Reservation;
import es.uca.iw.sss.spring.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventReservationRepository extends JpaRepository<EventReservation, Long> {
    Reservation findById(int id);

    List<EventReservation> findByUser(User user);
}
