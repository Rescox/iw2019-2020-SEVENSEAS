package es.uca.iw.sss.spring;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.sss.spring.utils.SecurityUtils;

@Route(value = "Welcome", layout = MainLayout.class)
@PageTitle("Welcome")
public class WelcomeView extends HorizontalLayout {

    public WelcomeView()
    {
        if(SecurityUtils.hasRole("customer")) {
            VerticalLayout verticalLayout1 = new VerticalLayout();
            H1 welcomeMessage = new H1("Bienvenido a su viaje con: ");
            User currentUser = SecurityUtils.getUser();
            Ship currentShip = currentUser.getShip();
            Grid<Ship> grid = new Grid<>(Ship.class);
            grid.setColumns("name", "licensePlate");
            grid.setItems(currentShip);

            Image shipplane = new Image("images/restaurants1.jpg", "images/restaurants1.jpg");
            shipplane.

            add(verticalLayout1);
        }
        if(SecurityUtils.hasRole("admin")) {
            Button register = new Button("Register Ship", event -> ShipForm());
            add(register);
        }
    }

    public void ShipForm() {
        UI.getCurrent().navigate(ShipForm.class);
        UI.getCurrent().getPage().reload();
    }
}
