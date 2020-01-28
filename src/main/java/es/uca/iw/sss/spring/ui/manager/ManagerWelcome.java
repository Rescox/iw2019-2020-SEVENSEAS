package es.uca.iw.sss.spring.ui.manager;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import es.uca.iw.sss.spring.backend.entities.*;
import es.uca.iw.sss.spring.backend.services.AccountService;
import es.uca.iw.sss.spring.backend.services.EventReservationService;
import es.uca.iw.sss.spring.backend.services.ShipService;
import es.uca.iw.sss.spring.backend.services.SpaReservationService;
import es.uca.iw.sss.spring.ui.common.MainLayout;
import org.springframework.security.access.annotation.Secured;

import java.text.ParseException;

@Secured("manager")
@Route(value = "WelcomeManager", layout = MainLayout.class)
public class ManagerWelcome extends VerticalLayout {
    private static final String[] MONTH_LABELS = new String[] {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
            "Aug", "Sep", "Oct", "Nov", "Dec"};
    private ShipService shipService;
    private AccountService accountService;
    private EventReservationService eventReservationService;
    private SpaReservationService spaReservationService;
    private Grid shipGrid;
    private Ship ship;

    public ManagerWelcome(AccountService accountService, ShipService shipService, EventReservationService eventReservationService, SpaReservationService spaReservationService) {
        this.shipService = shipService;
        this.accountService = accountService;
        this.spaReservationService = spaReservationService;
        this.eventReservationService = eventReservationService;
        this.shipGrid = new Grid<>(Ship.class);
        shipGrid.setItems(shipService.findAll());
        shipGrid.setColumns("id","name","licensePlate");
        shipGrid.asSingleSelect().addValueChangeListener(e -> {
            try {
                addChart((Ship) e.getValue());
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        });
        add(shipGrid);
    }


    public void addChart(Ship ship) throws ParseException {
        Long shipId = ship.getId();
        UI.getCurrent().navigate(ShipStatistics.class, shipId);
    }
}
