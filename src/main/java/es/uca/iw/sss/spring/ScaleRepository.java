package es.uca.iw.sss.spring;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScaleRepository extends JpaRepository<Scale,Long> {
    Scale findByDateStartsWithIgnoreCase(String entrance);
}
