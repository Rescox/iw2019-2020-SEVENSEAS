package es.uca.iw.sss.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Service
public class CityService {
    private CityRepository repoCity;
    private final HashMap<Long, City> cities = new HashMap<>();

    @Autowired
    public CityService(CityRepository cityRepository)
    {
        this.repoCity = cityRepository;
    }

    public synchronized void saveCity(City city){
        repoCity.save(city);
    }
    public synchronized List<City> findAll() {
        return repoCity.findAll();
    }

    public Optional<City> findById(Long id) {
        return repoCity.findById(id);
    }

    public City findByName(String name)
    {
        return repoCity.findByName(name);
    }

    public void create(City city)
    {
        repoCity.save(city);
    }
}