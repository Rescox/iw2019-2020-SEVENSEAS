package es.uca.iw.sss.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class ScaleService {
    private ScaleRepository repoScale;
    private final HashMap<Long, Scale> scales = new HashMap<>();

    @Autowired
    public ScaleService(ScaleRepository repoScale)
    {
        this.repoScale = repoScale;
    }

    public synchronized void saveScales(Scale scale){
        repoScale.save(scale);
    }
    public synchronized List<Scale> findAll() {
        return repoScale.findAll();
    }

    public Scale findById(int id) {
        return repoScale.findById(id);
    }

    public void create(Scale scale)
    {
        repoScale.save(scale);
    }
}