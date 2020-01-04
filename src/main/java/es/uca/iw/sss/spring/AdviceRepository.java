package es.uca.iw.sss.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

public interface AdviceRepository extends JpaRepository<Advice,Long> {
    Advice findById(int id);

    Advice findByAdvice(String advice);

    Advice findByAdvice(Advice userInstance);

    Advice findByAdviceStartsWithIgnoreCase(String advice);
}
