package es.uca.iw.sss.spring;

import org.springframework.data.jpa.repository.JpaRepository;


public interface DishRepository extends JpaRepository<Dish,Long> {
    Dish findByNameDishStartsWithIgnoreCase(String dish);
}
