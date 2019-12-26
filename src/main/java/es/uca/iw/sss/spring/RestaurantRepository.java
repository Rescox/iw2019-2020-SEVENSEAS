package es.uca.iw.sss.spring;

import es.uca.iw.sss.spring.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {

    Restaurant FindById(int id);

}
