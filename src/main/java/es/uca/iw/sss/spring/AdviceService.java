package es.uca.iw.sss.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
}