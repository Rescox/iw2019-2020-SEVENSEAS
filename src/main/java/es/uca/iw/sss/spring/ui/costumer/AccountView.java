package es.uca.iw.sss.spring.ui.costumer;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.sss.spring.backend.entities.*;
import es.uca.iw.sss.spring.backend.services.*;
import es.uca.iw.sss.spring.ui.common.MainLayout;
import org.springframework.security.access.annotation.Secured;

import java.util.Iterator;
import java.util.List;

import static es.uca.iw.sss.spring.utils.SecurityUtils.getUser;

@Secured("customer")
@Route(value = "Account", layout = MainLayout.class)
@PageTitle("Account")
public class AccountView extends FormLayout {
    private UserService userService;
    private AccountService accountService;
    private ReservationService restaurantService;
    private SpaReservationService spaService;
    private EventReservationService eventService;
    private Grid<User> userGrid = new Grid<>(User.class);
    private Grid<Account> accountGrid = new Grid<>(Account.class);
    private Grid<Reservation> restaurantGrid = new Grid<>(Reservation.class);
    private Grid<SpaReservation> spaGrid = new Grid<>(SpaReservation.class);
    private Grid<EventReservation> eventGrid = new Grid<>(EventReservation.class);

    public AccountView(UserService userService, AccountService accountService, ReservationService restaurantService, SpaReservationService spaService, EventReservationService eventService) {
        this.accountService = accountService;
        H2 DatosPersonales = new H2("Personal Details");
        H2 DatosCuenta = new H2("Account Details");
        H2 DatosRestaurante = new H2("Restaurant Details");
        H2 DatosSpa = new H2("Spa Details");
        H2 DatosEvent = new H2("Event Details");
        accountGrid.setColumns("date", "price", "serviceName");
        userGrid.setColumns("firstName", "lastName", "dni", "email", "user");
        restaurantGrid.setColumns("restaurant.name", "date");
        spaGrid.setColumns("spa.name","price","date");
        eventGrid.setColumns("event.name","price","date");
        restaurantGrid.setItems(restaurantService.findByUser(getUser()));
        accountGrid.setItems(accountService.findByUser(getUser()));
        spaGrid.setItems(spaService.findByUser(getUser()));
        eventGrid.setItems(eventService.findByUser(getUser()));
        List<Account> accountList = accountService.findByUser(getUser());
        List<SpaReservation> spaList = spaService.findByUser(getUser());
        List<EventReservation> eventReservations = eventService.findByUser(getUser());
        float totalSpent = 0;
        Iterator<Account> itAccount = accountList.iterator();
        Iterator<SpaReservation> itSpa = spaList.iterator();
        Iterator<EventReservation> itReservation = eventReservations.iterator();
        while(itAccount.hasNext()) {
            totalSpent += itAccount.next().getPrice();
        }
        while(itReservation.hasNext()) {
            totalSpent += itReservation.next().getPrice();
        }
        while(itSpa.hasNext()) {
            totalSpent += itSpa.next().getPrice();
        }
        H1 totalspent =new H1 ("Dinero Total Gastado: " + totalSpent);

        userGrid.setMaxHeight("100px");
        userGrid.setItems(getUser());

        VerticalLayout content = new VerticalLayout();
        content.add(DatosCuenta, accountGrid, DatosRestaurante, restaurantGrid, DatosSpa, spaGrid, DatosEvent, eventGrid, totalspent);
        add(content);
    }

}






