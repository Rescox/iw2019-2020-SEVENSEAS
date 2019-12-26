package es.uca.iw.sss.spring;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route(value = "ManageShip", layout = MainLayout.class)
@PageTitle("Manage Ship")
public class ManageShipView extends VerticalLayout {
    Grid<Ship> shipGrid = new Grid<>(Ship.class);
    ShipService shipService;

    public ManageShipView(ShipService shipService)
    {
        this.shipService = shipService;
        shipGrid.setColumns("licensePlate", "name");;
        shipGrid.setItems(shipService.findAll());
        Button register = new Button("Register Ship", event -> ShipForm());
        add(shipGrid,register);
    }

    public void ShipForm() {
        UI.getCurrent().navigate(ShipForm.class);
        UI.getCurrent().getPage().reload();
    }
}