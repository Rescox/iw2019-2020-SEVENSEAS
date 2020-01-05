package es.uca.iw.sss.spring;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Set;

import static es.uca.iw.sss.spring.utils.SecurityUtils.getUser;

@Route(value = "RestaurantDetails", layout = MainLayout.class)
@PageTitle("Restaurant")
public class RestaurantDetailsView extends FormLayout {

    private RestaurantService restaurantService;

    public RestaurantDetailsView(RestaurantService restaurantService)
    {
        this.restaurantService = restaurantService;
        User currentUser = getUser();
        Ship currentShip = currentUser.getShip();
        Set<Restaurant> restaurants = currentShip.getRestaurantSet();


        VerticalLayout content = new VerticalLayout();
        HorizontalLayout HL1 = new HorizontalLayout();
        Image img = new Image("images/ItalyFlavors.jpg","images/fashion1.jpg");
        HL1.add(img);
        content.add(HL1);
        add(content);
    }
}
