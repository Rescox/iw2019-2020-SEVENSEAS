package es.uca.iw.sss.spring.backend.services;

import es.uca.iw.sss.spring.backend.entities.Account;
import es.uca.iw.sss.spring.backend.entities.Reservation;
import es.uca.iw.sss.spring.backend.entities.User;
import es.uca.iw.sss.spring.backend.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Reservation> findByUser(User user) { return reservationRepository.findByUser(user); }

    public void create(Reservation reservation) {
        reservationRepository.save(reservation);
    }

}