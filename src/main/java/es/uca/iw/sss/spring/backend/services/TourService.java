package es.uca.iw.sss.spring.backend.services;

import es.uca.iw.sss.spring.backend.entities.Tour;
import es.uca.iw.sss.spring.backend.repositories.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourService {

  @Autowired
  TourRepository tourRepository;

  public TourService(TourRepository repo) {
    this.tourRepository = repo;
  }

  public Tour saveTour(Tour tour) {
    return tourRepository.save(tour);
  }

  public List<Tour> listTour() {
    return tourRepository.findAll();
  }

  public Long countTours() {
    return tourRepository.count();
  }

  public void deleteTour(Tour tour) {
    tourRepository.delete(tour);
  }

  public void create(Tour tour) {
    tourRepository.save(tour);
  }
}
