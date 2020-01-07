package es.uca.iw.sss.spring;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;


@Route(value = "ReservationForm", layout = MainLayout.class)
@PageTitle("ReservationForm")
public class ReservationForm extends FormLayout {

    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    DatePicker datePicker = new DatePicker();
    LocalDate now = LocalDate.now();
    TimePicker timePicker = new TimePicker();


    private Dialog dialog = new Dialog();
    private ReservationRepository reservationRepository;
    private ReservationService reservationService;
    private UserService userService;
    private UserRepository userRepository;
    private Reservation reservation = new Reservation();
    private BeanValidationBinder<Reservation> binder = new BeanValidationBinder<>(Reservation.class);

    @Autowired
    public ReservationForm(ReservationService reservationService, UserService userService) {

        FormLayout formLayout = new FormLayout();
        this.reservationService = reservationService;
        this.userService = userService;
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
        reservation.setId_client((long) 1);
        reservation.setDate(datePicker.getValue().toString());
        reservation.setHour(timePicker.getValue().toString());
        reservationService.create(reservation);
        UI.getCurrent().navigate(WelcomeView.class);
        UI.getCurrent().getPage().reload();
    }


}
