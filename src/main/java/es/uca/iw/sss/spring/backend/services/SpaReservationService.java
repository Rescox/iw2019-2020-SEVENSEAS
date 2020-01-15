package es.uca.iw.sss.spring.backend.services;

import es.uca.iw.sss.spring.SpaReservation;
import es.uca.iw.sss.spring.backend.entities.Spa;
import es.uca.iw.sss.spring.backend.entities.User;
import es.uca.iw.sss.spring.backend.repositories.SpaReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class SpaReservationService {

    private SpaReservationRepository spaReservationRepository;
    private static SpaReservationService stance;
    private static final Logger LOGGER = Logger.getLogger(SpaReservationService.class.getName());

    @Autowired
    public SpaReservationService(SpaReservationRepository repo) {
        super();
        this.spaReservationRepository = repo;
    }

    /**
     * @return a reference to an example facade for Customer objects.
     */
    public static SpaReservationService getInstance() {

        return stance;
    }


    public void create(SpaReservation spaReservation) {

        spaReservationRepository.save(spaReservation);
    }

    public List<SpaReservation> findByUser(User user) {
        return spaReservationRepository.findByUser(user);
    }

    public int countBySpa(Spa spa) { return spaReservationRepository.countBySpa(spa); }


}
