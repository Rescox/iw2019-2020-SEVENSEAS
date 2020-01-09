package es.uca.iw.sss.spring;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AdviceCityRepository extends JpaRepository<AdviceCity,Long> {
    AdviceCity findById(int id);

    AdviceCity findByAdvice(String advice);

    AdviceCity findByAdvice(AdviceCity userInstance);

    AdviceCity findByAdviceStartsWithIgnoreCase(String advice);
}
