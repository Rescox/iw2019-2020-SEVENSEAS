package es.uca.iw.sss.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
