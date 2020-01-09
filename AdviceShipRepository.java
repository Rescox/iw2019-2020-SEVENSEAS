package es.uca.iw.sss.spring;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdviceShipRepository extends JpaRepository<AdviceShip,Long> {
    AdviceShip findById(int id);

    AdviceShip findByAdvice(String advice);

    AdviceShip findByAdvice(AdviceShip userInstance);

    AdviceShip findByAdviceStartsWithIgnoreCase(String advice);
}
