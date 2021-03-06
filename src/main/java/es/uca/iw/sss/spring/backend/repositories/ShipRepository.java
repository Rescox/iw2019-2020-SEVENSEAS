package es.uca.iw.sss.spring.backend.repositories;
import es.uca.iw.sss.spring.backend.entities.Ship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShipRepository extends JpaRepository<Ship, Long> {
  Ship findById(long id);

  Ship findByLicensePlate(String licensePlate);

  List<Ship> findByNameStartsWithIgnoreCase(String name);

}
