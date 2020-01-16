package es.uca.iw.sss.spring.ui.costumer;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.*;
import es.uca.iw.sss.spring.backend.entities.Dish;
import es.uca.iw.sss.spring.backend.entities.Reservation;
import es.uca.iw.sss.spring.backend.entities.Restaurant;
import es.uca.iw.sss.spring.backend.repositories.DishRepository;
import es.uca.iw.sss.spring.backend.repositories.ReservationRepository;
import es.uca.iw.sss.spring.backend.repositories.RestaurantRepository;
import es.uca.iw.sss.spring.backend.services.DishService;
import es.uca.iw.sss.spring.backend.services.ReservationService;
import es.uca.iw.sss.spring.backend.services.RestaurantService;
import es.uca.iw.sss.spring.ui.common.MainLayout;
import org.springframework.beans.factory.annotation.Autowired;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import static es.uca.iw.sss.spring.utils.SecurityUtils.getUser;


@Route(value = "ReservationForm", layout = MainLayout.class)
@PageTitle("ReservationForm")
public class ReservationForm extends HorizontalLayout implements HasUrlParameter<Long>{

    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private DatePicker datePicker;
    private NumberField numberField;
    private LocalDate now = LocalDate.now();
    private TimePicker timePicker;
    private ReservationRepository reservationRepository;
    private RestaurantRepository restaurantRepository;
    private DishRepository dishRepository;
    private ReservationService reservationService;
    private RestaurantService restaurantService;
    private DishService dishService;
    private Reservation reservation = new Reservation();
    private BeanValidationBinder<Reservation> binder = new BeanValidationBinder<>(Reservation.class);
    private Restaurant restaurant;
    private H3 name;
    private Label description;
    private String photourl;
    private Set<Dish> dishList;
    private Grid<Dish> dishGrid = new Grid<>(Dish.class);
    private Image img;
    private ConfirmDialog dialog;

    @Autowired
    public ReservationForm(ReservationService reservationService,
                           RestaurantRepository restaurantRepository, RestaurantService restaurantService,
                           ReservationRepository reservationRepository, DishService DishService, DishRepository dishRepository)
    {

        //Pintar parte de registrar reserva
        datePicker = new DatePicker();
        timePicker = new TimePicker();
        numberField = new NumberField();

        this.reservationRepository = reservationRepository;
        this.reservationService = reservationService;
        this.restaurantRepository = restaurantRepository;
        this.restaurantService = restaurantService;
        this.name = new H3();
        this.photourl = new String();
        this.description = new Label();
        this.dishList = new HashSet<>();
        this.img = new Image();
        this.dialog = new ConfirmDialog();
        img.setHeight("100%");
        img.setWidth("250px");

        binder.bindInstanceFields(this);
        VerticalLayout verticalLayout1 = new VerticalLayout();
        VerticalLayout verticalLayout2 = new VerticalLayout();
        VerticalLayout verticalLayout3 = new VerticalLayout();
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
        numberField.setValue(1d);
        numberField.setHasControls(true);
        numberField.setMin(1);
        numberField.setLabel("Number of persons");

        Button register = new Button("Register", event -> {
            registrar();
        });
        Button cancel = new Button("Cancel",  event -> {
            UI.getCurrent().navigate(RestaurantView.class);
        });

        register.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        register.addClickShortcut(Key.ENTER);
        cancel.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        HorizontalLayout buttons = new HorizontalLayout(register, cancel);
        verticalLayout1.setWidth("20%");
        verticalLayout2.setWidth("60%");
        verticalLayout3.setWidth("20%");
        verticalLayout1.add(name,description,firstName, lastName, datePicker,timePicker,numberField,buttons);
        verticalLayout2.add(dishGrid);
        verticalLayout3.add(img);

        add(verticalLayout3,verticalLayout1,verticalLayout2);

    }

    private void registrar() {
        if(firstName.getValue() != "" && lastName.getValue() != "" && timePicker.getValue().toString() != "" && datePicker.getValue().toString() != "")
        {
            dialog.open();
        }
    }

    private void onCancel(ConfirmDialog.CancelEvent cancelEvent) {
        UI.getCurrent().navigate(RestaurantView.class);
    }


    private boolean enoughAforum(){

        String dateHour1 = reservation.getDate() +" "+reservation.getHour();
        Date parsedDateHour1 = null;
        try {
            parsedDateHour1 = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(dateHour1);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        List<Reservation> reservations = reservationRepository.findByRestaurant(restaurant);
        Long cont = 0L;
        for(Reservation r: reservations)
        {
            String dateHour2 = r.getDate() +" "+r.getHour();
            Date parsedDateHour2 = null;
            try {
                parsedDateHour2 = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(dateHour2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(parsedDateHour1.equals(parsedDateHour2))
            {
                cont = cont + r.getPersons();
            }
        }

        if(cont + reservation.getPersons() > restaurant.getAforum())
        {
            return false;
        }
        else return true;

    }


    private void onPublish(ConfirmDialog.ConfirmEvent confirmEvent){
        reservation.setFirstName(firstName.getValue());
        reservation.setLastName(lastName.getValue());
        reservation.setUser(getUser());
        reservation.setDate(datePicker.getValue().toString());
        reservation.setHour(timePicker.getValue().toString());
        reservation.setPersons(numberField.getValue().longValue());
        if(enoughAforum())
        {
            reservationService.create(reservation);
            UI.getCurrent().navigate(WelcomeView.class);
            UI.getCurrent().getPage().reload();
        }
    }


    @Override
    public void setParameter(BeforeEvent beforeEvent, Long id) {

        Location location = beforeEvent.getLocation();
        System.out.println(location.getSegments().get(1));
        id = Long.parseLong(location.getSegments().get(1));
        if(restaurantRepository.findById(id).isPresent()){
            reservation.setRestaurant(restaurantRepository.findById(id).get());
            restaurant = restaurantService.findById(id).get();
            this.name.setText(restaurant.getName());
            this.description.setText(restaurant.getDescription());
            this.photourl = restaurant.getPhoto();
            this.img.setSrc(this.photourl);
            this.img.setAlt(this.photourl);
            this.dishList = restaurant.getDishSet();
            dishGrid.setItems(this.dishList);
            dishGrid.setColumns("nameDish","price");
            dialog.setHeader("Confirm reservation");
            dialog.setText("The final import will be added to your account");
            dialog.setConfirmButton("Yes",this::onPublish);
            dialog.setCancelButton("No", this::onCancel);


        }

    }

}
