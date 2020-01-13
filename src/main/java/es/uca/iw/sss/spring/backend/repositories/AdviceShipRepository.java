package es.uca.iw.sss.spring.backend.repositories;

import es.uca.iw.sss.spring.backend.entities.AdviceShip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdviceShipRepository extends JpaRepository<AdviceShip, Long> {
  AdviceShip findById(int id);

  AdviceShip findByAdvice(String advice);

  AdviceShip findByAdvice(AdviceShip userInstance);

  List<AdviceShip> findByAdviceStartsWithIgnoreCase(String advice);
}
