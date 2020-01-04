package es.uca.iw.sss.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class AdviceService {
    private AdviceRepository repoAdvice;
    private final HashMap<Long, Advice> advices = new HashMap<>();
    private static AdviceService adviceService;

    @Autowired
    public AdviceService(AdviceRepository repoAdvice)
    {
        this.repoAdvice = repoAdvice;
    }

    public synchronized void saveAdvice(Advice advice){
        repoAdvice.save(advice);
    }
    public synchronized List<Advice> findAll() {
        return repoAdvice.findAll();
    }

    public Advice findById(int id) {
        return repoAdvice.findById(id);
    }

    public static AdviceService getInstance(AdviceRepository repoAdvice) {
        if (adviceService == null) {
            adviceService = new AdviceService(repoAdvice);
            adviceService.findAll();
        }
        return adviceService;
    }

    public void create(Advice advice)
    {
        repoAdvice.save(advice);
    }
}