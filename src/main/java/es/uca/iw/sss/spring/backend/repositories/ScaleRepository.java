package es.uca.iw.sss.spring.backend.repositories;

import es.uca.iw.sss.spring.backend.entities.Scale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScaleRepository extends JpaRepository<Scale, Long> {
  List<Scale> findByDateStartsWithIgnoreCase(String entrance);
}
