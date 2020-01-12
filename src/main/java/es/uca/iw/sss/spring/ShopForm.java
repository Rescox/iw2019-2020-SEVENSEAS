package es.uca.iw.sss.spring;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class ShopForm extends VerticalLayout implements KeyNotifier {
  private final ShopRepository shopRepository;
  private Shop shop;
  private TextField photo = new TextField("Photo");
  private TextField description = new TextField("Description");
  private TextField name = new TextField("Name");
  private TextField licensePlate = new TextField("Ship License Plate");
  private ShopService shopService;
  private ShipService shipService;
  Button save = new Button("Save", VaadinIcon.CHECK.create());
  Button cancel = new Button("Cancel");
  Button delete = new Button("Delete", VaadinIcon.TRASH.create());
  HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);
  private ChangeHandler changeHandler;

  @Autowired
  public ShopForm(ShopRepository shopRepository, ShopService shopService, ShipService shipService) {
    this.shopRepository = shopRepository;
    this.shopService = shopService;
    this.shipService = shipService;
    add(name, description, photo, licensePlate, actions);

    setSpacing(true);

    save.getElement().getThemeList().add("primary");
    delete.getElement().getThemeList().add("error");

    addKeyPressListener(Key.ENTER, e -> save());

    save.addClickListener(e -> save());
    delete.addClickListener(e -> delete());
    cancel.addClickListener(e -> editShop(shop));
    setVisible(false);
  }

  void delete() {
    shopRepository.delete(shop);
    changeHandler.onChange();
  }

  void save() {
    shop.setPhoto(photo.getValue());
    shop.setName(name.getValue());
    shop.setDescription(description.getValue());
    shop.setShip(shipService.findByLicensePlate(licensePlate.getValue()));
    shopService.create(shop);
    changeHandler.onChange();
  }

  public interface ChangeHandler {
    void onChange();
  }

  public final void editShop(Shop shopEdit) {
    if (shopEdit == null) {
      setVisible(false);
      return;
    }
    final boolean persisted = shopEdit.getId() != null;
    if (persisted) {
      shop = shopRepository.findById(shopEdit.getId()).get();
    } else {
      shop = shopEdit;
    }
    cancel.setVisible(persisted);

    if (shop.getPhoto() != null) {
      photo.setValue(shop.getPhoto());
    } else {
      photo.setValue("");
    }
    if (shop.getName() != null) {
      name.setValue(shop.getName());
    } else {
      name.setValue("");
    }
    if (shop.getDescription() != null) {
      description.setValue(shop.getDescription());
    } else {
      description.setValue("");
    }
    if (shop.getShip() != null) {
      licensePlate.setValue(shop.getShip().getLicensePlate());
    } else {
      licensePlate.setValue("");
    }

    setVisible(true);

    name.focus();
  }

  public void setChangeHandler(ChangeHandler h) {
    changeHandler = h;
  }
}
