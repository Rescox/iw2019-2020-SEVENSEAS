package es.uca.iw.sss.spring.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish,Long> {
  List<Dish> findByNameDishStartsWithIgnoreCase(String dish);
}
