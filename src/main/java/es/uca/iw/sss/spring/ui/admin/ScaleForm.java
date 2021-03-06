package es.uca.iw.sss.spring.ui.admin;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import es.uca.iw.sss.spring.backend.entities.City;
import es.uca.iw.sss.spring.backend.entities.Scale;
import es.uca.iw.sss.spring.backend.repositories.ScaleRepository;
import es.uca.iw.sss.spring.backend.services.CityService;
import es.uca.iw.sss.spring.backend.services.ScaleService;
import es.uca.iw.sss.spring.backend.services.ShipService;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class ScaleForm extends VerticalLayout implements KeyNotifier {
  private final ScaleRepository scaleRepository;
  private Scale scale;
  private City city;
  private TextField date = new TextField("Date");
  private TextField licensePlate = new TextField("Ship License Plate");
  private TextField cityName = new TextField("City Name");
  private BeanValidationBinder<Scale> binder = new BeanValidationBinder<>(Scale.class);
  private CityService cityService;
  private ScaleService scaleService;
  private ShipService shipService;
  private Button save = new Button("Save", VaadinIcon.CHECK.create());
  private Button cancel = new Button("Reset");
  private Button delete = new Button("Delete", VaadinIcon.TRASH.create());
  private HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);
  private ChangeHandler changeHandler;

  @Autowired
  public ScaleForm(
      ScaleRepository scaleRepository,
      ScaleService scaleService,
      ShipService shipService,
      CityService cityService) {
    this.scaleRepository = scaleRepository;
    this.scaleService = scaleService;
    this.shipService = shipService;
    this.cityService = cityService;
    add(cityName, date, licensePlate, actions);

    binder.bindInstanceFields(this);
    setSpacing(true);

    save.getElement().getThemeList().add("primary");
    delete.getElement().getThemeList().add("error");

    addKeyPressListener(Key.ENTER, e -> save());

    save.addClickListener(e -> save());
    delete.addClickListener(e -> delete());
    cancel.addClickListener(e -> editScale(scale));
    setVisible(false);
  }

  private void delete() {
    scaleRepository.delete(scale);
    changeHandler.onChange();
    UI.getCurrent().getPage().reload();
  }

  private void save() {
    scale.setDate(date.getValue());
    scale.setShip(shipService.findByLicensePlate(licensePlate.getValue()));
    if (cityService.findByName(cityName.getValue()) != null) {
      scale.setCity(cityService.findByName(cityName.getValue()));
    } else {
      city = new City(cityName.getValue());
      city.setScale(scale);
      cityService.create(city);
      scale.setCity(cityService.findByName(cityName.getValue()));
    }
    scaleService.create(scale);
    changeHandler.onChange();
    UI.getCurrent().getPage().reload();
  }

  public interface ChangeHandler {
    void onChange();
  }

  public final void editScale(Scale scaleEdit) {
    if (scaleEdit == null) {
      setVisible(false);
      return;
    }
    final boolean persisted = scaleEdit.getId() != null;
    if (persisted) {
      scale = scaleRepository.findById(scaleEdit.getId()).get();
    } else {
      scale = scaleEdit;
    }
    cancel.setVisible(persisted);

    if (scale.getDate() != null) {
      date.setValue(scale.getDate());
    } else {
      date.setValue("");
    }
    if (scale.getShip() != null) {
      this.licensePlate.setEnabled(false);
      licensePlate.setValue(scale.getShip().getLicensePlate());
    } else {
      licensePlate.setValue("");
    }
    if (scale.getCity() != null) {
      cityName.setValue(scale.getCity().getName());
    } else {
        cityName.setValue("");
    }
      setVisible(true);

      cityName.focus();
  }

    public void setChangeHandler(ChangeHandler h) {
        changeHandler = h;
    }

    public TextField getShipPlate() {
        return licensePlate;
    }
}
