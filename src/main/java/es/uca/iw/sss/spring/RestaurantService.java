package es.uca.iw.sss.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class RestaurantService {

    private RestaurantRepository restaurantRepository;
    private static RestaurantService stance;
    private static final Logger LOGGER = Logger.getLogger(RestaurantService.class.getName());

    @Autowired
    public RestaurantService(RestaurantRepository repo) {
        super();
        this.restaurantRepository = repo;
    }

    /**
     * @return a reference to an example facade for Customer objects.
     */
    public static RestaurantService getInstance() {

        return stance;
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





