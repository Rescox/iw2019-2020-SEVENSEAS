package es.uca.iw.sss.spring;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;

@Route(value = "ManageShip", layout = MainLayout.class)
@PageTitle("Manage Ship")
public class ManageShipView extends VerticalLayout {
    final TextField filter;
    final Grid<Ship> shipGrid;
    private final ShipRepository shipRepository;
    private final ShipForm shipForm;
    private final Button addShip;
    private final Button restaurants;
    private final Button scales;
    private final Button tour;
    private final Button advices;
    final Ship[] shipSelected = new Ship[1];

    public ManageShipView(ShipRepository shipRepository, ShipForm shipForm)
    {
        this.shipRepository = shipRepository;
        this.shipForm = shipForm;
        this.shipGrid = new Grid<>(Ship.class);
        this.filter = new TextField();
        this.addShip = new Button("New ship", VaadinIcon.PLUS.create());
        this.restaurants = new Button("Manage Restaurants", e -> RestaurantView());
        this.tour = new Button("Manage Tour", e -> TourView());
        this.scales = new Button("Manage Scale", e -> ScaleView());
        this.advices = new Button("Manage Advices", e -> AdviceView());
        advices.setEnabled(false);

        HorizontalLayout actions = new HorizontalLayout(filter, addShip,restaurants, tour,advices,scales);
        add(actions, shipGrid, shipForm);

        shipGrid.setColumns("id","name","licensePlate","plane","legend");
        filter.setPlaceholder("Filter by name");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listShips(e.getValue()));


        shipGrid.asSingleSelect().addValueChangeListener(e -> {
            shipForm.editShip(e.getValue());
            shipSelected[0] = e.getValue(); //Barco seleccionado
            advices.setEnabled(true);
        });

        addShip.addClickListener(e -> shipForm.editShip(new Ship()));

        shipForm.setChangeHandler(() -> {
            shipForm.setVisible(false);
            listShips(filter.getValue());
        });

        listShips(null);

    }

    void listShips(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            shipGrid.setItems(shipRepository.findAll());
        }
        else {
            shipGrid.setItems(shipRepository.findByNameStartsWithIgnoreCase(filterText));
        }
    }

    public void RestaurantView()
    {
        UI.getCurrent().navigate(ManageRestaurantView.class);
        UI.getCurrent().getPage().reload();
    }

    public void AdviceView()
    {
        UI.getCurrent().navigate(ManageAdviceView.class, shipSelected[0].getLicensePlate());
        UI.getCurrent().getPage().reload();
    }

    public void ScaleView()
    {
        UI.getCurrent().navigate(ManageScaleView.class);
        UI.getCurrent().getPage().reload();
    }

    public void TourView()
    {
        UI.getCurrent().navigate(ManageTourView.class);
        UI.getCurrent().getPage().reload();
    }
}

