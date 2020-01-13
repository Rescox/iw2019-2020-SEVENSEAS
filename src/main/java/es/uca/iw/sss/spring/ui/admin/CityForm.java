package es.uca.iw.sss.spring.ui.admin;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import es.uca.iw.sss.spring.backend.entities.City;
import es.uca.iw.sss.spring.backend.repositories.CityRepository;
import es.uca.iw.sss.spring.backend.services.CityService;
import es.uca.iw.sss.spring.backend.services.ScaleService;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class CityForm extends VerticalLayout implements KeyNotifier {
  private final CityRepository cityRepository;
  private City city;
  private TextField pic = new TextField("Picture");
  private TextField name = new TextField("Name");
  private TextField scale = new TextField("Id Scale");
  private CityService cityService;
  private ScaleService scaleService;
  Button save = new Button("Save", VaadinIcon.CHECK.create());
  Button cancel = new Button("Cancel");
  Button delete = new Button("Delete", VaadinIcon.TRASH.create());
  HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);
  private ChangeHandler changeHandler;

  @Autowired
  public CityForm(
      CityRepository cityRepository, CityService cityService, ScaleService scaleService) {
    this.cityRepository = cityRepository;
    this.cityService = cityService;
    this.scaleService = scaleService;
    add(name, pic, scale, actions);

    setSpacing(true);

    save.getElement().getThemeList().add("primary");
    delete.getElement().getThemeList().add("error");

    addKeyPressListener(Key.ENTER, e -> save());
    save.addClickListener(e -> save());
    delete.addClickListener(e -> delete());
    cancel.addClickListener(e -> editCity(city));
    setVisible(false);
  }

  void delete() {
    cityRepository.delete(city);
    changeHandler.onChange();
  }

  void save() {
    city.setName(name.getValue());
    city.setPic(pic.getValue());
    city.setScale(scaleService.findById(Long.parseLong(scale.getValue())).get());
    cityService.create(city);
    changeHandler.onChange();
  }

  public interface ChangeHandler {
    void onChange();
  }

  public final void editCity(City cityEdit) {
    if (cityEdit == null) {
      setVisible(false);
      return;
    }
    final boolean persisted = cityEdit.getId() != null;
    if (persisted) {
      city = cityRepository.findById(cityEdit.getId()).get();
    } else {
      city = cityEdit;
    }
    cancel.setVisible(persisted);

    if (city.getPic() != null) {
      pic.setValue(city.getPic());
    } else {
      pic.setValue("");
    }
    name.setValue(city.getName());
    if (city.getScale() != null) {
      scale.setValue(Long.toString(city.getScale().getId()));
    } else {
      scale.setValue("");
    }

    setVisible(true);

    name.focus();
  }

  public void setChangeHandler(ChangeHandler h) {
    changeHandler = h;
  }
}
