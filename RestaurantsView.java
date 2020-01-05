package es.uca.iw.sss.spring;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.sss.spring.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static es.uca.iw.sss.spring.utils.SecurityUtils.getUser;

@Route(value = "Restaurant", layout = MainLayout.class)
@PageTitle("Restaurant")
public class RestaurantsView extends FormLayout {
    private Grid<Restaurant> restaurantGrid = new Grid<>(Restaurant.class);

    private RestaurantService restaurantService;
    public RestaurantsView(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
        User currentUser = SecurityUtils.getUser();
        Ship currentShip = currentUser.getShip();
        Set<Restaurant> restaurants = currentShip.getRestaurantSet();
        VerticalLayout verticalLayout1 = new VerticalLayout();
        restaurantGrid.setColumns("id");
        restaurantGrid.setItems(restaurants);
        verticalLayout1.add(restaurantGrid);
        add(verticalLayout1);
    }



}

/*
@Route(value = "Restaurant", layout = MainLayout.class)
@PageTitle("Reservation")
public class RestaurantsView extends VerticalLayout {


    private RestaurantService restaurantService;
    private Grid<Restaurant> restaurantGrid = new Grid<>(Restaurant.class);

    @Autowired
    public RestaurantsView(RestaurantService restaurantService)
    {
        this.restaurantService = restaurantService;
        User currentUser = SecurityUtils.getUser();
        Ship currentShip = currentUser.getShip();
        Set<Restaurant> restaurants = currentShip.getRestaurantSet();
        VerticalLayout verticalLayout1 = new VerticalLayout();

    }

}
*/