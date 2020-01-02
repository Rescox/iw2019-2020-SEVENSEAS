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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static es.uca.iw.sss.spring.utils.SecurityUtils.getUser;

@Route(value ="RestaurantsView", layout = MainLayout.class)
@PageTitle("Restaurants")
public class RestaurantsView extends VerticalLayout {


    private RestaurantService restaurantService;
    private Grid<Restaurant> restaurantGrid = new Grid<>(Restaurant.class);

    public RestaurantsView(RestaurantService restaurantService)
    {
        this.restaurantService = restaurantService;
        User currentUser = SecurityUtils.getUser();
        Ship currentShip = currentUser.getShip();
        Set<Restaurant> restaurants = currentShip.getRestaurantSet();
        VerticalLayout verticalLayout1 = new VerticalLayout();
        restaurantGrid.setColumns("restaurant");
        restaurantGrid.setItems(restaurants);
        verticalLayout1.add(restaurantGrid);
        add(verticalLayout1);
    }

}
