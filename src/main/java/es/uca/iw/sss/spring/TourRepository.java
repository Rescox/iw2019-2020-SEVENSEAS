package es.uca.iw.sss.spring;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourRepository extends JpaRepository<Tour,Long> {

    Tour findById(int id);

    List<Tour> findAll();

    List<Tour> findByNameStartsWithIgnoreCase(String name);
}