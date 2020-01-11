package es.uca.iw.sss.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository repo) {
        this.restaurantRepository = repo;
    }

    public Restaurant saveRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> listRestaurant() {
        return restaurantRepository.findAll();
    }

    public Long countRestaurants() {
        return restaurantRepository.count();
    }

    public void deleteRestaurant(Restaurant restaurant) {
        restaurantRepository.delete(restaurant);
    }

    public void create(Restaurant restaurant)
    {
        restaurantRepository.save(restaurant);
    }

    public Optional<Restaurant> findById(Long id) {
        return restaurantRepository.findById(id);
    }
}