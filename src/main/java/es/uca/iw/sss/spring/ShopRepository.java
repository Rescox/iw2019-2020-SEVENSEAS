package es.uca.iw.sss.spring;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop,Long> {
    Shop findById(int id);

}
