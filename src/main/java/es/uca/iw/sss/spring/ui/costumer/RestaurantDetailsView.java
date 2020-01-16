package es.uca.iw.sss.spring.ui.costumer;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.sss.spring.backend.entities.Restaurant;
import es.uca.iw.sss.spring.backend.entities.Ship;
import es.uca.iw.sss.spring.backend.entities.User;
import es.uca.iw.sss.spring.backend.services.RestaurantService;
import es.uca.iw.sss.spring.ui.common.MainLayout;
import org.springframework.security.access.annotation.Secured;

import java.util.Set;

import static es.uca.iw.sss.spring.utils.SecurityUtils.getUser;

@Secured("customer")
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
