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
import es.uca.iw.sss.spring.backend.entities.Restaurant;
import es.uca.iw.sss.spring.backend.repositories.RestaurantRepository;
import es.uca.iw.sss.spring.backend.services.RestaurantService;
import es.uca.iw.sss.spring.backend.services.ShipService;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class RestaurantForm extends VerticalLayout implements KeyNotifier {
  private final RestaurantRepository restaurantRepository;
  private Restaurant restaurant;
  private TextField aforum = new TextField("Aforum");
  private TextField photo = new TextField("Photo");
  private TextField description = new TextField("Description");
  private TextField name = new TextField("Name");
  private TextField phone = new TextField("Phone");
  private TextField licensePlate = new TextField("Ship License Plate");
  private RestaurantService restaurantService;
  private ShipService shipService;
  private Button save = new Button("Save", VaadinIcon.CHECK.create());
  private Button cancel = new Button("Reset");
  private Button delete = new Button("Delete", VaadinIcon.TRASH.create());
  private HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);
  private ChangeHandler changeHandler;

  @Autowired
  public RestaurantForm(
      RestaurantRepository restaurantRepository,
      RestaurantService restaurantService,
      ShipService shipService) {
    this.restaurantRepository = restaurantRepository;
    this.restaurantService = restaurantService;
    this.shipService = shipService;
    add(name, description, aforum, photo, phone, licensePlate, actions);

    setSpacing(true);

    save.getElement().getThemeList().add("primary");
    delete.getElement().getThemeList().add("error");

    addKeyPressListener(Key.ENTER, e -> save());

    save.addClickListener(e -> save());
    delete.addClickListener(e -> delete());
    cancel.addClickListener(e -> editRestaurant(restaurant));
    setVisible(false);
  }

  private void delete() {
    restaurantRepository.delete(restaurant);
    changeHandler.onChange();
    UI.getCurrent().getPage().reload();
  }

  private void save() {
    restaurant.setAforum(Long.parseLong(aforum.getValue()));
    restaurant.setPhoto(photo.getValue());
    restaurant.setName(name.getValue());
    restaurant.setDescription(description.getValue());
    restaurant.setPhone(phone.getValue());
    restaurant.setShip(shipService.findByLicensePlate(licensePlate.getValue()));
    restaurantService.create(restaurant);
    changeHandler.onChange();
    UI.getCurrent().getPage().reload();
  }

  public interface ChangeHandler {
    void onChange();
  }

  public final void editRestaurant(Restaurant restaurantEdit) {
    if (restaurantEdit == null) {
      setVisible(false);
      return;
    }
    final boolean persisted = restaurantEdit.getId() != null;
    if (persisted) {
      restaurant = restaurantRepository.findById(restaurantEdit.getId()).get();
    } else {
      restaurant = restaurantEdit;
    }
    cancel.setVisible(persisted);

    if (restaurant.getAforum() != null) {
      aforum.setValue(Long.toString(restaurant.getAforum()));
    } else {
      aforum.setValue("");
    }
    if (restaurant.getPhoto() != null) {
      photo.setValue(restaurant.getPhoto());
    } else {
      photo.setValue("");
    }
    if (restaurant.getName() != null) {
      name.setValue(restaurant.getName());
    } else {
      name.setValue("");
    }
    if (restaurant.getDescription() != null) {
      description.setValue(restaurant.getDescription());
    } else {
      description.setValue("");
    }
    if (restaurant.getPhone() != null) {
      phone.setValue(restaurant.getPhone());
    } else {
      phone.setValue("");
    }
    if (restaurant.getShip() != null) {
      this.licensePlate.setEnabled(false);
      licensePlate.setValue(restaurant.getShip().getLicensePlate());
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
