package es.uca.iw.sss.spring.ui.admin;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.*;
import es.uca.iw.sss.spring.backend.entities.Restaurant;
import es.uca.iw.sss.spring.backend.repositories.RestaurantRepository;
import es.uca.iw.sss.spring.backend.services.ShipService;
import es.uca.iw.sss.spring.ui.common.MainLayout;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;

import java.util.Set;

@Secured("admin")
@Route(value = "ManageRestaurant", layout = MainLayout.class)
@PageTitle("Manage Restaurant")
public class ManageRestaurantView extends VerticalLayout implements HasUrlParameter<String> {
  final TextField filter;
  final Grid<Restaurant> restaurantGrid;
  private final RestaurantRepository restaurantRepository;
  private final RestaurantForm restaurantForm;
  private final Button addRestaurant;
  private final Button dish;
  private ShipService shipService;
  private Set<Restaurant> restaurants;
  private Restaurant[] restaurantSelected = new Restaurant[1];

  public ManageRestaurantView(
      RestaurantRepository restaurantRepository,
      RestaurantForm restaurantForm,
      ShipService shipService) {
    this.restaurantRepository = restaurantRepository;
    this.shipService = shipService;
    this.restaurantForm = restaurantForm;
    this.restaurantGrid = new Grid<>(Restaurant.class);
    this.filter = new TextField();
    this.addRestaurant = new Button("New Restaurant", VaadinIcon.PLUS.create());
    this.dish = new Button("Manage Dish", e -> DishView());
    dish.setVisible(false);
    HorizontalLayout actions = new HorizontalLayout(filter, addRestaurant, dish);
    add(actions, restaurantGrid, restaurantForm);

    restaurantGrid.setColumns(
        "id", "name", "description", "aforum", "photo", "phone", "ship.licensePlate");
    restaurantGrid.getColumnByKey("ship.licensePlate").setHeader("Ship License Plate");

    filter.setPlaceholder("Filter by name");
    filter.setValueChangeMode(ValueChangeMode.EAGER);
    filter.addValueChangeListener(e -> listRestaurants(e.getValue()));

    restaurantGrid
        .asSingleSelect()
        .addValueChangeListener(
                e -> {
                    restaurantForm.editRestaurant(e.getValue());
                    restaurantSelected[0] = e.getValue();
                    if (dish.isVisible()) {
                        dish.setVisible(false);
                    } else {
                        dish.setVisible(true);
                    }
                });

      addRestaurant.addClickListener(e -> {
          restaurantForm.getShipPlate().setEnabled(true);
          restaurantForm.editRestaurant(new Restaurant());
      });
      restaurantForm.setChangeHandler(
              () -> {
                  restaurantForm.setVisible(false);
                  listRestaurants(filter.getValue());
              });
      listRestaurants(null);
  }

  void listRestaurants(String filterText) {
    if (StringUtils.isEmpty(filterText)) {
      restaurantGrid.setItems(restaurantRepository.findAll());
    } else {
      restaurantGrid.setItems(restaurantRepository.findByNameStartsWithIgnoreCase(filterText));
    }
  }

  public void DishView() {
    UI.getCurrent().navigate(ManageDishView.class, restaurantSelected[0].getId());
  }

  @Override
  public void setParameter(BeforeEvent beforeEvent, String licensePlate) {
    Location location = beforeEvent.getLocation();
    licensePlate = location.getSegments().get(1);
    restaurants = shipService.findByLicensePlate(licensePlate).getRestaurantSet();
    restaurantGrid.setItems(restaurants);
  }
}
