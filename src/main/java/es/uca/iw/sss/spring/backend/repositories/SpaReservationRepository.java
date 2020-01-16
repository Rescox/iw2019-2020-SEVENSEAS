package es.uca.iw.sss.spring.backend.repositories;

import es.uca.iw.sss.spring.backend.entities.Spa;
import es.uca.iw.sss.spring.backend.entities.SpaReservation;
import es.uca.iw.sss.spring.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpaReservationRepository extends JpaRepository<SpaReservation,Long> {
    SpaReservation findById(int id);
    List<SpaReservation> findByUser(User user);
    int countBySpa(Spa event);
    List<SpaReservation> findBySpa(Spa spa);

}
