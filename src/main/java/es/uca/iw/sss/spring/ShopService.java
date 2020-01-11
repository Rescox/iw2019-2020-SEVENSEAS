package es.uca.iw.sss.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    @Autowired
    ShopRepository shopRepository;

    public ShopService(ShopRepository repo) {
        this.shopRepository = repo;
    }

    public Shop saveShop(Shop shop) {
        return shopRepository.save(shop);
    }

    public List<Shop> listShop() {
        return shopRepository.findAll();
    }

    public Long countShops() {
        return shopRepository.count();
    }

    public void deleteShop(Shop shop) {
        shopRepository.delete(shop);
    }

    public void create(Shop shop)
    {
        shopRepository.save(shop);
    }
}