package es.uca.iw.sss.spring;

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

@Route(value = "ManageShip", layout = MainLayout.class)
@PageTitle("Manage Ship")
public class ManageShipView extends VerticalLayout {
    final TextField filter;
    final Grid<Ship> shipGrid;
    private final ShipRepository shipRepository;
    private final ShipForm shipForm;
    private final Button addShip;

    public ManageShipView(ShipRepository shipRepository, ShipForm shipForm)
    {
        this.shipRepository = shipRepository;
        this.shipForm = shipForm;
        this.shipGrid = new Grid<>(Ship.class);
        this.filter = new TextField();
        this.addShip = new Button("New ship", VaadinIcon.PLUS.create());

        HorizontalLayout actions = new HorizontalLayout(filter, addShip);
        add(actions, shipGrid, shipForm);

        shipGrid.setColumns("name","licensePlate","plane");;
        filter.setPlaceholder("Filter by name");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listCustomers(e.getValue()));

        shipGrid.asSingleSelect().addValueChangeListener(e -> {
            shipForm.editCustomer(e.getValue());
        });
        addShip.addClickListener(e -> shipForm.editCustomer(new Ship("", "", "")));
        shipForm.setChangeHandler(() -> {
            shipForm.setVisible(false);
            listCustomers(filter.getValue());
        });
        listCustomers(null);
    }

    void listCustomers(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            shipGrid.setItems(shipRepository.findAll());
        }
        else {
            shipGrid.setItems(shipRepository.findByNameStartsWithIgnoreCase(filterText));
        }
    }
}

