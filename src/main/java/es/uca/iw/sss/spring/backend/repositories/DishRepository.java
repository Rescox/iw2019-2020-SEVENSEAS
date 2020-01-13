package es.uca.iw.sss.spring.backend.repositories;
import es.uca.iw.sss.spring.backend.entities.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DishRepository extends JpaRepository<Dish,Long> {
    List<Dish> findByNameDishStartsWithIgnoreCase(String dish);
}
