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

@Route(value = "ManageDish", layout = MainLayout.class)
@PageTitle("Manage Dish")
public class ManageDishView extends VerticalLayout implements HasUrlParameter<Long> {
    final TextField filter;
    final Grid<Dish> dishGrid;
    private final DishRepository dishRepository;
    private final DishForm dishForm;
    private final Button addScale;
    private final RestaurantService restaurantService;
    private Set<Dish> dishSet;

    public ManageDishView(DishRepository dishRepository, DishForm dishForm, RestaurantService restaurantService)
    {
        this.dishRepository = dishRepository;
        this.restaurantService = restaurantService;
        this.dishForm = dishForm;
        this.dishGrid = new Grid<>(Dish.class);
        this.filter = new TextField();
        this.addScale = new Button("New Dish", VaadinIcon.PLUS.create());

        HorizontalLayout actions = new HorizontalLayout(filter, addScale);
        add(actions, dishGrid, dishForm);

        dishGrid.setColumns("id","nameDish","price");

        filter.setPlaceholder("Filter by name");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listDishes(e.getValue()));

        dishGrid.asSingleSelect().addValueChangeListener(e -> {
            dishForm.editDish(e.getValue());
        });
        addScale.addClickListener(e -> dishForm.editDish(new Dish()));
        dishForm.setChangeHandler(() -> {
            dishForm.setVisible(false);
            listDishes(filter.getValue());
        });
        listDishes(null);
    }

    void listDishes(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            dishGrid.setItems(dishRepository.findAll());
        }
        else {
            dishGrid.setItems(dishRepository.findByNameDishStartsWithIgnoreCase(filterText));
        }
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Long id) {
        Location location = beforeEvent.getLocation();
        id = Long.parseLong(location.getSegments().get(1));
        dishSet = restaurantService.findById(id).get().getDishSet();
        dishGrid.setItems(dishSet);
    }
}

