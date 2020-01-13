package es.uca.iw.sss.spring;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
    List<Restaurant> findByNameStartsWithIgnoreCase(String name);
}
