package es.uca.iw.sss.spring;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository <Reservation,Long> {

        Reservation findById(int id);
        List<Reservation> findByRestaurant(Restaurant restaurant);

}
