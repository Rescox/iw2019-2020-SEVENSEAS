package es.uca.iw.sss.spring;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.sss.spring.utils.SecurityUtils;

import java.util.Set;

@Route(value = "Welcome", layout = MainLayout.class)
@PageTitle("Welcome")
public class WelcomeView extends HorizontalLayout {

    public WelcomeView()
    {
        if(SecurityUtils.hasRole("customer")) {
            VerticalLayout verticalLayout1 = new VerticalLayout();
            User currentUser = SecurityUtils.getUser();
            Ship currentShip = currentUser.getShip();
            Set<Scale> scaleSet = currentShip.getScaleSet();
            H2 shipMap = new H2("Map");
            Grid<Ship> gridShip = new Grid<>(Ship.class);
            Grid<Scale> gridScale = new Grid<>(Scale.class);
            gridShip.setColumns("name", "licensePlate");
            gridScale.setColumns("date", "city.name");
            gridScale.setItems(scaleSet);
            gridShip.setItems(currentShip);
            verticalLayout1.add(gridShip, gridScale);
            add(verticalLayout1);
        }
        if(SecurityUtils.hasRole("admin")) {

        }
    }

    public void ShipForm() {
        UI.getCurrent().navigate(ShipForm.class);
        UI.getCurrent().getPage().reload();
    }
}
