package es.uca.iw.sss.spring;

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
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class DishForm extends VerticalLayout implements KeyNotifier {
  private final DishRepository dishRepository;
  private Dish dishes;
  private TextField name = new TextField("Name");
  private TextField price = new TextField("Price");
  private TextField restaurantId = new TextField("Restaurant id");
  private DishService dishService;
  private RestaurantService restaurantService;
  Button save = new Button("Save", VaadinIcon.CHECK.create());
  Button cancel = new Button("Cancel");
  Button delete = new Button("Delete", VaadinIcon.TRASH.create());
  HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);
  private ChangeHandler changeHandler;

  @Autowired
  public DishForm(
      DishRepository dishRepository,
      DishService dishService,
      RestaurantService restaurantService) {
    this.dishRepository = dishRepository;
    this.dishService = dishService;
    this.restaurantService = restaurantService;
    add(name,price, restaurantId, actions);

    setSpacing(true);

    save.getElement().getThemeList().add("primary");
    delete.getElement().getThemeList().add("error");

    addKeyPressListener(Key.ENTER, e -> save());

    save.addClickListener(e -> save());
    delete.addClickListener(e -> delete());
    cancel.addClickListener(e -> editDish(dishes));
    setVisible(false);
  }

  void delete() {
    dishRepository.delete(dishes);
    changeHandler.onChange();
    UI.getCurrent().getPage().reload();
  }

  void save() {
    dishes.setNameDish(name.getValue());
    dishes.setPrice(Float.parseFloat(price.getValue()));
    dishes.setRestaurant(restaurantService.findById(Long.parseLong(restaurantId.getValue())).get());
    dishService.create(dishes);
    changeHandler.onChange();
    UI.getCurrent().getPage().reload();
  }

  public interface ChangeHandler {
    void onChange();
  }

  public final void editDish(Dish dishEdit) {
    if (dishEdit == null) {
      setVisible(false);
      return;
    }
    final boolean persisted = dishEdit.getId() != null;
    if (persisted) {
      dishes = dishRepository.findById(dishEdit.getId()).get();
    } else {
      dishes = dishEdit;
    }
    cancel.setVisible(persisted);

    name.setValue(dishes.getNameDish());
    price.setValue(Float.toString(dishes.getPrice()));
    if (dishes.getRestaurant() != null) {
      restaurantId.setValue(Long.toString(dishes.getRestaurant().getId()));
    } else {
      restaurantId.setValue("");
    }

    setVisible(true);

    name.focus();
  }

  public void setChangeHandler(ChangeHandler h) {
    changeHandler = h;
  }
}
