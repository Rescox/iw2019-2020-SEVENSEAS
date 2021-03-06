
package es.uca.iw.sss.spring.backend.services;

import es.uca.iw.sss.spring.backend.entities.Spa;
import es.uca.iw.sss.spring.backend.repositories.SpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpaService {

    @Autowired
    SpaRepository spaRepository;

    public SpaService(SpaRepository repo) {
        this.spaRepository = repo;
    }

    public Spa saveSpa(Spa spa) {
        return spaRepository.save(spa);
    }

    public List<Spa> listSpa() {
        return spaRepository.findAll();
    }

    public Long countSpa() {
        return spaRepository.count();
    }

    public void deleteSpa(Spa spa) {
        spaRepository.delete(spa);
    }

    public void create(Spa spa)
    {
        spaRepository.save(spa);
    }

    public Optional<Spa> findById(Long id) {
        return spaRepository.findById(id);
    }

    public Optional<Spa> findByUser(Long id) {
        return spaRepository.findById(id);
    }



}