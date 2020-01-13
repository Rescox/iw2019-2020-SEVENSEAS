package es.uca.iw.sss.spring.backend.repositories;

import es.uca.iw.sss.spring.backend.entities.AdviceCity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AdviceCityRepository extends JpaRepository<AdviceCity,Long> {
    AdviceCity findById(int id);

    AdviceCity findByAdvice(String advice);

    AdviceCity findByAdvice(AdviceCity userInstance);

    List<AdviceCity> findByAdviceStartsWithIgnoreCase(String advice);
}
