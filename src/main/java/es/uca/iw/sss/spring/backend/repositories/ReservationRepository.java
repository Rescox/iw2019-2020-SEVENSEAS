package es.uca.iw.sss.spring.backend.repositories;

import es.uca.iw.sss.spring.backend.entities.Account;
import es.uca.iw.sss.spring.backend.entities.Reservation;
import es.uca.iw.sss.spring.backend.entities.Restaurant;
import es.uca.iw.sss.spring.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository <Reservation,Long> {

        Reservation findById(int id);
        List<Reservation> findByUser(User user);
        List<Reservation> findByRestaurant(Restaurant restaurant);

}
