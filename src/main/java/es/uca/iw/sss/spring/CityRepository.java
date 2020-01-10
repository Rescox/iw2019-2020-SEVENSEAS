package es.uca.iw.sss.spring;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CityRepository extends JpaRepository<City,Long> {
    City findByNameStartsWithIgnoreCase(String city);
    City findByName(String city);
}
