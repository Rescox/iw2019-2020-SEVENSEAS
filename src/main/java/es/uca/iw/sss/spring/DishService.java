package es.uca.iw.sss.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Service
public class DishService {
    private DishRepository repoDish;
    private final HashMap<Long, Dish> dishes = new HashMap<>();

    @Autowired
    public DishService(DishRepository repoDish)
    {
        this.repoDish = repoDish;
    }

    public synchronized void saveCity(Dish dish){
        repoDish.save(dish);
    }
    public synchronized List<Dish> findAll() {
        return repoDish.findAll();
    }

    public Optional<Dish> findById(Long id) {
        return repoDish.findById(id);
    }

    public void create(Dish dish)
    {
        repoDish.save(dish);
    }
}