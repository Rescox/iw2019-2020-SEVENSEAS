package es.uca.iw.sss.spring.backend.repositories;

import es.uca.iw.sss.spring.backend.entities.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Long> {
  List<Shop> findByNameStartsWithIgnoreCase(String name);
}
