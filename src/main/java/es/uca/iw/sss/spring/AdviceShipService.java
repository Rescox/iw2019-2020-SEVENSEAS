package es.uca.iw.sss.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class AdviceShipService {
    private AdviceShipRepository repoAdvice;
    private final HashMap<Long, AdviceShip> advices = new HashMap<>();

    @Autowired
    public AdviceShipService(AdviceShipRepository repoAdvice)
    {
        this.repoAdvice = repoAdvice;
    }

    public synchronized void saveAdvice(AdviceShip advice){
        repoAdvice.save(advice);
    }
    public synchronized List<AdviceShip> findAll() {
        return repoAdvice.findAll();
    }

    public AdviceShip findById(int id) {
        return repoAdvice.findById(id);
    }

    public void create(AdviceShip advice)
    {
        repoAdvice.save(advice);
    }
}