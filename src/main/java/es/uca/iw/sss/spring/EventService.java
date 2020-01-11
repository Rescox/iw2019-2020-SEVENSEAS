package es.uca.iw.sss.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    public EventService(EventRepository repo) {
        this.eventRepository = repo;
    }

    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> listEvent() {
        return eventRepository.findAll();
    }

    public Long countEvent() {
        return eventRepository.count();
    }

    public void deleteSpa(Event event) {
        eventRepository.delete(event);
    }

    public void create(Event event)
    {
        eventRepository.save(event);
    }
}