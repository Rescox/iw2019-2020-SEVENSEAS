package es.uca.iw.sss.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class AdviceShipService {
    private AdviceShipRepository repoAdvice;
    private final HashMap<Long, AdviceShip> advices = new HashMap<>();
    private static AdviceShipService adviceService;

    @Autowired
    public AdviceShipService(AdviceShipRepository repoAdvice)
    {
        this.repoAdvice = repoAdvice;
    }

    public synchronized void saveAdvice(AdviceShip adviceShip){
        repoAdvice.save(adviceShip);
    }
    public synchronized List<AdviceShip> findAll() {
        return repoAdvice.findAll();
    }

    public AdviceShip findById(int id) {
        return repoAdvice.findById(id);
    }

    public static AdviceShipService getInstance(AdviceShipRepository repoAdvice) {
        if (adviceService == null) {
            adviceService = new AdviceShipService(repoAdvice);
            adviceService.findAll();
        }
        return adviceService;
    }

    public void create(AdviceShip adviceShip)
    {
        repoAdvice.save(adviceShip);
    }
}