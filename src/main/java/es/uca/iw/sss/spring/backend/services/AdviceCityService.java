package es.uca.iw.sss.spring.backend.services;

import es.uca.iw.sss.spring.backend.entities.AdviceCity;
import es.uca.iw.sss.spring.backend.repositories.AdviceCityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


@Service
public class AdviceCityService {
    private AdviceCityRepository repoAdvice;
    private final HashMap<Long, AdviceCity> advices = new HashMap<>();

    @Autowired
    public AdviceCityService(AdviceCityRepository repoAdvice)
    {
        this.repoAdvice = repoAdvice;
    }

    public synchronized void saveAdvice(AdviceCity advice){
        repoAdvice.save(advice);
    }
    public synchronized List<AdviceCity> findAll() {
        return repoAdvice.findAll();
    }

    public AdviceCity findById(int id) {
        return repoAdvice.findById(id);
    }

    public void create(AdviceCity advice)
    {
        repoAdvice.save(advice);
    }
}