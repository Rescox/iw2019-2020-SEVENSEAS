package es.uca.iw.sss.spring;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository <Reservation,Long> {

        Reservation findById(int id);



}
