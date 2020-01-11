package es.uca.iw.sss.spring;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class ShopService {
    private ShopRepository repoShop;
    private final HashMap<Long, Shop> ships = new HashMap<>();


    private ShopRepository shopRepository;
    private static ShopService stance;
    private static final Logger LOGGER = Logger.getLogger(ShopService.class.getName());

    @Autowired
    public ShopService(ShopRepository repo) {
        super();
        this.shopRepository = repo;
    }

    /**
     * @return a reference to an example facade for Customer objects.
     */
    public static ShopService getInstance() {

        return stance;
    }
}
