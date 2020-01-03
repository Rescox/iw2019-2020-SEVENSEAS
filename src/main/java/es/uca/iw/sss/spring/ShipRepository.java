package es.uca.iw.sss.spring;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipRepository extends JpaRepository<Ship,Long> {
    Ship findById(int id);

    Ship findByLicensePlate(String licensePlate);

    Ship findByNameStartsWithIgnoreCase(String name);
}
