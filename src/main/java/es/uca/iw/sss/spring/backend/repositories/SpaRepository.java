package es.uca.iw.sss.spring.backend.repositories;

import es.uca.iw.sss.spring.backend.entities.Spa;
import es.uca.iw.sss.spring.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpaRepository extends JpaRepository<Spa, Long> {

  Spa findById(int id);

  List<Spa> findAll();

  List<Spa> findByNameStartsWithIgnoreCase(String name);

}
