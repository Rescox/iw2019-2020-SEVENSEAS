package es.uca.iw.sss.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

@Route(value = "ManageRestaurant", layout = MainLayout.class)
@PageTitle("Manage Restaurant")
public class ManageRestaurantView extends VerticalLayout implements HasUrlParameter<String> {
    final TextField filter;
    final Grid<Restaurant> restaurantGrid;
    private final RestaurantRepository restaurantRepository;
    private final RestaurantForm restaurantForm;
    private final Button addRestaurant;
    private  ShipService shipService;
    private Set<Restaurant> restaurants;

    public ManageRestaurantView(RestaurantRepository restaurantRepository, RestaurantForm restaurantForm, ShipService shipService)
    {
        this.restaurantRepository = restaurantRepository;
        this.shipService = shipService;
        this.restaurantForm = restaurantForm;
        this.restaurantGrid = new Grid<>(Restaurant.class);
        this.filter = new TextField();
        this.addRestaurant = new Button("New Restaurant", VaadinIcon.PLUS.create());

        HorizontalLayout actions = new HorizontalLayout(filter, addRestaurant);
        add(actions, restaurantGrid, restaurantForm);

        restaurantGrid.setColumns("id","name","description","aforum","photo");
        filter.setPlaceholder("Filter by entrance");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listRestaurants(e.getValue()));

        restaurantGrid.asSingleSelect().addValueChangeListener(e -> {
            restaurantForm.editRestaurant(e.getValue());
        });
        addRestaurant.addClickListener(e -> restaurantForm.editRestaurant(new Restaurant()));
        restaurantForm.setChangeHandler(() -> {
            restaurantForm.setVisible(false);
            listRestaurants(filter.getValue());
        });
        listRestaurants(null);
    }

    void listRestaurants(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            restaurantGrid.setItems(restaurantRepository.findAll());
        }
        else {
            restaurantGrid.setItems(restaurantRepository.findByNameStartsWithIgnoreCase(filterText));
        }
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String licensePlate) {
        Location location = beforeEvent.getLocation();
        licensePlate = location.getSegments().get(1);
        restaurants = shipService.findByLicensePlate(licensePlate).getRestaurantSet();
        restaurantGrid.setItems(restaurants);
    }
}

