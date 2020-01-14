package es.uca.iw.sss.spring.ui.costumer;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.sss.spring.backend.entities.Restaurant;
import es.uca.iw.sss.spring.backend.entities.Ship;
import es.uca.iw.sss.spring.backend.entities.User;
import es.uca.iw.sss.spring.backend.repositories.RestaurantRepository;
import es.uca.iw.sss.spring.backend.services.ReservationService;
import es.uca.iw.sss.spring.backend.services.RestaurantService;
import es.uca.iw.sss.spring.backend.services.UserService;
import es.uca.iw.sss.spring.ui.common.MainLayout;

import java.util.Set;

import static es.uca.iw.sss.spring.utils.SecurityUtils.getUser;

@Route(value = "RestaurantView", layout = MainLayout.class)
@PageTitle("Restaurant")
public class RestaurantView extends AppLayout {

    private Grid<Restaurant> restaurantGrid = new Grid<>(Restaurant.class);
    private ReservationService reservationService;
    private UserService userService;
    private RestaurantRepository restaurantRepository;


    private RestaurantService restaurantService;
    public RestaurantView(RestaurantService restaurantService) {

        //Datos
        this.restaurantService = restaurantService;
        User currentUser = getUser();
        Ship currentShip = currentUser.getShip();
        Set<Restaurant> restaurants = currentShip.getRestaurantSet();

        //Layouts
        VerticalLayout verticalLayout1 = new VerticalLayout();
        verticalLayout1.setWidth("30%");

        //Mostrar todos los nombres de los restaurantes
        for(Restaurant r: restaurants)
        {
            Button boton = new Button(r.getName(), e -> ReservationForm(r.getId()));
            verticalLayout1.add(boton);
        }

        setContent(verticalLayout1);
    }

    public void ReservationForm(Long id_restaurante)
    {

        UI.getCurrent().navigate(ReservationForm.class, id_restaurante);
        UI.getCurrent().getPage().reload();
    }


}
