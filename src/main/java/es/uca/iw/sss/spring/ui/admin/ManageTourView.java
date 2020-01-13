package es.uca.iw.sss.spring.ui.admin;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.*;
import es.uca.iw.sss.spring.backend.entities.Tour;
import es.uca.iw.sss.spring.backend.repositories.TourRepository;
import es.uca.iw.sss.spring.backend.services.ShipService;
import es.uca.iw.sss.spring.ui.common.MainLayout;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;

import java.util.Set;

@Secured("admin")
@Route(value = "ManageTour", layout = MainLayout.class)
@PageTitle("Manage Tour")
public class ManageTourView extends VerticalLayout implements HasUrlParameter<String> {
  final TextField filter;
  final Grid<Tour> tourGrid;
  private final TourRepository tourRepository;
  private final TourForm tourForm;
  private final Button addTour;
  private Set<Tour> tours;
  private ShipService shipService;

  public ManageTourView(TourRepository tourRepository, TourForm tourForm, ShipService shipService) {
    this.tourRepository = tourRepository;
    this.shipService = shipService;
    this.tourForm = tourForm;
    this.tourGrid = new Grid<>(Tour.class);
    this.filter = new TextField();
    this.addTour = new Button("New Tour", VaadinIcon.PLUS.create());

    HorizontalLayout actions = new HorizontalLayout(filter, addTour);
    add(actions, tourGrid, tourForm);

    tourGrid.setColumns("id", "name", "description", "price", "schedule", "ship.licensePlate");
    tourGrid.getColumnByKey("ship.licensePlate").setHeader("Ship License Plate");
    filter.setPlaceholder("Filter by name");
    filter.setValueChangeMode(ValueChangeMode.EAGER);
    filter.addValueChangeListener(e -> listTours(e.getValue()));

    tourGrid
        .asSingleSelect()
        .addValueChangeListener(
            e -> {
              tourForm.editTour(e.getValue());
            });
    addTour.addClickListener(e -> tourForm.editTour(new Tour()));
    tourForm.setChangeHandler(
        () -> {
          tourForm.setVisible(false);
          listTours(filter.getValue());
        });
    listTours(null);
  }

  void listTours(String filterText) {
    if (StringUtils.isEmpty(filterText)) {
      tourGrid.setItems(tourRepository.findAll());
    } else {
      tourGrid.setItems(tourRepository.findByNameStartsWithIgnoreCase(filterText));
    }
  }

  @Override
  public void setParameter(BeforeEvent beforeEvent, String licensePlate) {
    Location location = beforeEvent.getLocation();
    licensePlate = location.getSegments().get(1);
    tours = shipService.findByLicensePlate(licensePlate).getTourSet();
    tourGrid.setItems(tours);
  }
}
