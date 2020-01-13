/*package es.uca.iw.sss.spring.ui.costumer;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.*;
import es.uca.iw.sss.spring.backend.entities.Reservation;
import es.uca.iw.sss.spring.backend.entities.Restaurant;
import es.uca.iw.sss.spring.backend.repositories.ReservationRepository;
import es.uca.iw.sss.spring.backend.repositories.RestaurantRepository;
import es.uca.iw.sss.spring.backend.repositories.UserRepository;
import es.uca.iw.sss.spring.backend.services.ReservationService;
import es.uca.iw.sss.spring.backend.services.UserService;
import es.uca.iw.sss.spring.ui.admin.ManageShipView;
import es.uca.iw.sss.spring.ui.common.MainLayout;
import es.uca.iw.sss.spring.ui.common.WelcomeView;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import static es.uca.iw.sss.spring.utils.SecurityUtils.getUser;


@Route(value = "ReservationForm", layout = MainLayout.class)
@PageTitle("ReservationForm")
public class ReservationForm extends FormLayout implements HasUrlParameter<Long>{

    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    DatePicker datePicker;
    LocalDate now = LocalDate.now();
    TimePicker timePicker;


    private Dialog dialog = new Dialog();
    private ReservationRepository reservationRepository;
    private RestaurantRepository restaurantRepository;
    private ReservationService reservationService;
    private UserService userService;
    private UserRepository userRepository;
    private Reservation reservation = new Reservation();
    private BeanValidationBinder<Reservation> binder = new BeanValidationBinder<>(Reservation.class);
    private Restaurant restaurant = new Restaurant();

    @Autowired
    public ReservationForm(ReservationService reservationService, UserService userService, RestaurantRepository restaurantRepository) {

        datePicker = new DatePicker();
        timePicker = new TimePicker();
        FormLayout formLayout = new FormLayout();
        this.reservationService = reservationService;
        this.userService = userService;
        this.restaurantRepository = restaurantRepository;
        VerticalLayout verticalLayout = new VerticalLayout();
        firstName.setRequiredIndicatorVisible(true);
        lastName.setRequiredIndicatorVisible(true);
        datePicker.setLabel("Choose a day");
        datePicker.setMin(now.withDayOfMonth(1));
        datePicker.setMax(now.withDayOfMonth(now.lengthOfMonth()));
        datePicker.setRequiredIndicatorVisible(true);
        timePicker.setRequiredIndicatorVisible(true);
        timePicker.setLabel("Choose a time");
        timePicker.setMin("13:00");
        timePicker.setMax("23:00");

        binder.bindInstanceFields(this);

        Button register = new Button("Register", event -> {
            dialog.close();
            registerReservation();

        });
        Button cancel = new Button("Cancel",  event -> {
            dialog.close();
            UI.getCurrent().navigate(ManageShipView.class);
        });

        dialog.add(register, cancel);
        register.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        register.addClickShortcut(Key.ENTER);
        cancel.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        HorizontalLayout buttons = new HorizontalLayout(register, cancel);
        verticalLayout.add(firstName, lastName, datePicker,timePicker,buttons);
        formLayout.add(verticalLayout);
        add(formLayout);
    }

    private void registerReservation() {
        reservation.setFirstName(firstName.getValue());
        reservation.setLastName(lastName.getValue());
        reservation.setUser(getUser());
        reservation.setDate(datePicker.getValue().toString());
        reservation.setHour(timePicker.getValue().toString());
        reservationService.create(reservation);
        UI.getCurrent().navigate(WelcomeView.class);
        UI.getCurrent().getPage().reload();
    }

    public void setParameter(BeforeEvent beforeEvent, Long id) {

        Location location = beforeEvent.getLocation();
        System.out.println(location.getSegments().get(1));
        if(restaurantRepository.findById(id).isPresent()){
            reservation.setRestaurant(restaurantRepository.findById(id).get());
        }

    }

}*/
