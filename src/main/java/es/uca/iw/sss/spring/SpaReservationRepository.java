package es.uca.iw.sss.spring;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpaReservationRepository extends JpaRepository<SpaReservation,Long> {
    SpaReservation findById(int id);
}
