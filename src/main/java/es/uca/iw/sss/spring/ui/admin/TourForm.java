package es.uca.iw.sss.spring.ui.admin;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import es.uca.iw.sss.spring.backend.entities.Tour;
import es.uca.iw.sss.spring.backend.repositories.TourRepository;
import es.uca.iw.sss.spring.backend.services.ShipService;
import es.uca.iw.sss.spring.backend.services.TourService;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class TourForm extends VerticalLayout implements KeyNotifier {
  private final TourRepository tourRepository;
  private Tour tour;
  private TextField name = new TextField("Name");
  private TextField description = new TextField("Description");
  private TextField price = new TextField("Price");
  private TextField schedule = new TextField("Schedule");
  private TextField licensePlate = new TextField("Ship License Plate");
  private ShipService shipService;
  private TourService tourService;
  private Button save = new Button("Save", VaadinIcon.CHECK.create());
  private Button cancel = new Button("Reset");
  private Button delete = new Button("Delete", VaadinIcon.TRASH.create());
  private HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);
  private ChangeHandler changeHandler;

  @Autowired
  public TourForm(TourRepository tourRepository, TourService tourService, ShipService shipService) {
    this.tourRepository = tourRepository;
    this.shipService = shipService;
    this.tourService = tourService;
    add(name, description, price, schedule, licensePlate, actions);

    setSpacing(true);

    save.getElement().getThemeList().add("primary");
    delete.getElement().getThemeList().add("error");

    addKeyPressListener(Key.ENTER, e -> save());

    save.addClickListener(e -> save());
    delete.addClickListener(e -> delete());
    cancel.addClickListener(e -> editTour(tour));
    setVisible(false);
  }

  private void delete() {
    tourRepository.delete(tour);
    changeHandler.onChange();
    UI.getCurrent().getPage().reload();
  }

  private void save() {
    tour.setName(name.getValue());
    tour.setPrice(Float.parseFloat(price.getValue()));
    tour.setDescription(description.getValue());
    tour.setSchedule(schedule.getValue());
    tour.setShip(shipService.findByLicensePlate(licensePlate.getValue()));
    tourService.create(tour);
    changeHandler.onChange();
    UI.getCurrent().getPage().reload();
  }

  public interface ChangeHandler {
    void onChange();
  }

  public final void editTour(Tour tourEdit) {
    if (tourEdit == null) {
      setVisible(false);
      return;
    }
    final boolean persisted = tourEdit.getId() != null;
    if (persisted) {
      tour = tourRepository.findById(tourEdit.getId()).get();
    } else {
      tour = tourEdit;
    }
    cancel.setVisible(persisted);

    name.setValue(tour.getName());
    if (tour.getPrice() != null) {
      price.setValue(Float.toString(tour.getPrice()));
    } else {
      price.setValue("");
    }
    if (tour.getDescription() != null) {
      description.setValue(tour.getDescription());
    } else {
      description.setValue("");
    }
    if (tour.getSchedule() != null) {
      schedule.setValue(tour.getSchedule());
    } else {
      schedule.setValue("");
    }
    if (tour.getShip() != null) {
      this.licensePlate.setEnabled(false);
      licensePlate.setValue(tour.getShip().getLicensePlate());
    } else {
      licensePlate.setValue("");
    }

      setVisible(true);

      name.focus();
  }

    public void setChangeHandler(ChangeHandler h) {
        changeHandler = h;
    }

    public TextField getShipPlate() {
        return licensePlate;
    }
}
