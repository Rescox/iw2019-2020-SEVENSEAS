package es.uca.iw.sss.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}