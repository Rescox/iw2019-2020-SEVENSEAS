package es.uca.iw.sss.spring;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CityRepository extends JpaRepository<City,Long> {
    List<City> findByNameStartsWithIgnoreCase(String city);
    City findByName(String city);
}
