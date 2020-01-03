package es.uca.iw.sss.spring;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {

    Restaurant findById(int id);

    List<Restaurant> findAll();


}