package es.uca.iw.sss.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ReservationService {

    private ReservationRepository reservationRepository;
    private static ReservationService stance;
    private static final Logger LOGGER = Logger.getLogger(ReservationService.class.getName());

    @Autowired
    public ReservationService(ReservationRepository repo) {
        super();
        this.reservationRepository = repo;
    }

    /**
     * @return a reference to an example facade for Customer objects.
     */
    public static ReservationService getInstance() {

        return stance;
    }

    public void create(Reservation reservation) {
        reservationRepository.save(reservation);
    }

}