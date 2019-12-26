package es.uca.iw.sss.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class ShipService {
    private ShipRepository repoShip;
    private final HashMap<Long, Ship> ships = new HashMap<>();


    @Autowired
    public ShipService(ShipRepository repoShip)
    {
        this.repoShip = repoShip;
    }

    public synchronized void saveShip(Ship ship){
        repoShip.save(ship);
    }
    public synchronized List<Ship> findAll() {
        return repoShip.findAll();
    }

    public Ship findById(int id) {

        return repoShip.findById(id);

    }

    public Ship getLicensePlate(String licensePlate) { return repoShip.findByLicensePlate(licensePlate); }

    public void create(Ship ship)
    {
        repoShip.save(ship);
    }
}
