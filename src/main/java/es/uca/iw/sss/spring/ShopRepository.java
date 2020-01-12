package es.uca.iw.sss.spring;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop,Long> {

    Shop findById(int id);

    List<Shop> findAll();

    List<Shop> findByNameStartsWithIgnoreCase(String name);
}