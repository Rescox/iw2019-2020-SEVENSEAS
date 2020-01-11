package es.uca.iw.sss.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {


}
