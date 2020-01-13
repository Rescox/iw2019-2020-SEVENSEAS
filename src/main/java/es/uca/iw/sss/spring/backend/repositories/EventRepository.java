package es.uca.iw.sss.spring.backend.repositories;
import es.uca.iw.sss.spring.backend.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

  Event findById(int id);

  List<Event> findAll();

  List<Event> findByNameStartsWithIgnoreCase(String name);
}
