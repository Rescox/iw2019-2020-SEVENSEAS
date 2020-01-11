package es.uca.iw.sss.spring;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpaRepository extends JpaRepository<Spa,Long> {

    Spa findById(int id);

    List<Spa> findAll();

    Spa findByNameStartsWithIgnoreCase(String name);
}