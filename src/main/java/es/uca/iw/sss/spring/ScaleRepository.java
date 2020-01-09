package es.uca.iw.sss.spring;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScaleRepository extends JpaRepository<Scale,Long> {
    Scale findById(int id);

    Scale findByDateStartsWithIgnoreCase(String entrance);
}
