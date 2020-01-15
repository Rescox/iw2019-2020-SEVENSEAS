package es.uca.iw.sss.spring.backend.services;

import es.uca.iw.sss.spring.backend.entities.Event;
import es.uca.iw.sss.spring.backend.entities.EventReservation;
import es.uca.iw.sss.spring.backend.entities.User;
import es.uca.iw.sss.spring.backend.repositories.EventReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class EventReservationService {
    private EventReservationRepository eventReservationRepository;
    private static EventReservationService stance;
    private static final Logger LOGGER = Logger.getLogger(EventReservationService.class.getName());

    @Autowired
    public EventReservationService(EventReservationRepository repo) {
        super();
        this.eventReservationRepository = repo;
    }

    /**
     * @return a reference to an example facade for Customer objects.
     */
    public static EventReservationService getInstance() {

        return stance;
    }


    public void create(EventReservation eventReservation) {

        eventReservationRepository.save(eventReservation);
    }

    public List<EventReservation>  findByUser(User user) {
        return eventReservationRepository.findByUser(user);
    }

    public int countByEvent(Event event) { return eventReservationRepository.countByEvent(event); }

}
