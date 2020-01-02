package es.uca.iw.sss.spring;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository repo) {
        this.restaurantRepository = repo;
    }

    public Restaurant saveRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> listAdvice() {
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
}


