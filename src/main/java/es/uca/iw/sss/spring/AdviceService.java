package es.uca.iw.sss.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class AdviceService {
    @Autowired
    AdviceRepository adviceRepository;

    public AdviceService(AdviceRepository repo) {
        this.adviceRepository = repo;
    }

    public Advice saveAdvice(Advice advice) {
        return adviceRepository.save(advice);
    }

    public List<Advice> listAdvice() {
        return adviceRepository.findAll();
    }

    public Long countAdvices() {
        return adviceRepository.count();
    }

    public void deleteAdvices(Advice advice) {
        adviceRepository.delete(advice);
    }

    public void create(Advice advice)
    {
        adviceRepository.save(advice);
    }
}