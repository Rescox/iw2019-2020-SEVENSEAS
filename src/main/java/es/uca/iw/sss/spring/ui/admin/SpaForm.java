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
import es.uca.iw.sss.spring.backend.entities.Spa;
import es.uca.iw.sss.spring.backend.repositories.SpaRepository;
import es.uca.iw.sss.spring.backend.services.ShipService;
import es.uca.iw.sss.spring.backend.services.SpaService;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class SpaForm extends VerticalLayout implements KeyNotifier {
  private final SpaRepository spaRepository;
  private Spa spa;
  private TextField aforum = new TextField("Aforum");
  private TextField photo = new TextField("Photo");
  private TextField description = new TextField("Description");
  private TextField name = new TextField("Name");
  private TextField phone = new TextField("Phone");
  private TextField licensePlate = new TextField("Ship License Plate");
  private SpaService spaService;
  private ShipService shipService;
  Button save = new Button("Save", VaadinIcon.CHECK.create());
  Button cancel = new Button("Cancel");
  Button delete = new Button("Delete", VaadinIcon.TRASH.create());
  HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);
  private ChangeHandler changeHandler;

  @Autowired
  public SpaForm(SpaRepository spaRepository, SpaService spaService, ShipService shipService) {
    this.spaRepository = spaRepository;
    this.spaService = spaService;
    this.shipService = shipService;
    add(name, description, aforum, photo, phone, licensePlate, actions);

    setSpacing(true);

    save.getElement().getThemeList().add("primary");
    delete.getElement().getThemeList().add("error");

    addKeyPressListener(Key.ENTER, e -> save());

    save.addClickListener(e -> save());
    delete.addClickListener(e -> delete());
    cancel.addClickListener(e -> editSpa(spa));
    setVisible(false);
  }

  void delete() {
    spaRepository.delete(spa);
    changeHandler.onChange();
    UI.getCurrent().getPage().reload();
  }

  void save() {
    spa.setAforum(Long.parseLong(aforum.getValue()));
    spa.setPhoto(photo.getValue());
    spa.setName(name.getValue());
    spa.setDescription(description.getValue());
    spa.setPhone(phone.getValue());
    spa.setShip(shipService.findByLicensePlate(licensePlate.getValue()));
    spaService.create(spa);
    changeHandler.onChange();
    UI.getCurrent().getPage().reload();
  }

  public interface ChangeHandler {
    void onChange();
  }

  public final void editSpa(Spa spaEdit) {
    if (spaEdit == null) {
      setVisible(false);
      return;
    }
    final boolean persisted = spaEdit.getId() != null;
    if (persisted) {
      spa = spaRepository.findById(spaEdit.getId()).get();
    } else {
      spa = spaEdit;
    }
    cancel.setVisible(persisted);

    if (spa.getAforum() != null) {
      aforum.setValue(Long.toString(spa.getAforum()));
    } else {
      aforum.setValue("");
    }
    if (spa.getPhoto() != null) {
      photo.setValue(spa.getPhoto());
    } else {
      photo.setValue("");
    }
    if (spa.getName() != null) {
      name.setValue(spa.getName());
    } else {
      name.setValue("");
    }
    if (spa.getDescription() != null) {
      description.setValue(spa.getDescription());
    } else {
      description.setValue("");
    }
    if (spa.getShip() != null) {
      licensePlate.setValue(spa.getShip().getLicensePlate());
    } else {
      licensePlate.setValue("");
    }
    if (spa.getPhone() != null) {
      phone.setValue(spa.getPhone());
    } else {
      phone.setValue("");
    }

    setVisible(true);

    name.focus();
  }

  public void setChangeHandler(ChangeHandler h) {
    changeHandler = h;
  }
}
