package es.uca.iw.sss.spring.ui.costumer;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.*;
import es.uca.iw.sss.spring.ServicesView;
import es.uca.iw.sss.spring.backend.entities.Event;
import es.uca.iw.sss.spring.backend.entities.EventReservation;
import es.uca.iw.sss.spring.backend.repositories.EventRepository;
import es.uca.iw.sss.spring.backend.repositories.EventReservationRepository;
import es.uca.iw.sss.spring.backend.repositories.UserRepository;
import es.uca.iw.sss.spring.backend.services.EventReservationService;
import es.uca.iw.sss.spring.backend.services.EventService;
import es.uca.iw.sss.spring.backend.services.UserService;
import es.uca.iw.sss.spring.ui.common.MainLayout;
import es.uca.iw.sss.spring.ui.common.WelcomeView;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;

import static es.uca.iw.sss.spring.utils.SecurityUtils.getUser;

@Route(value = "EventView", layout = MainLayout.class)
@PageTitle("Event")
public class EventView extends HorizontalLayout implements HasUrlParameter<Long> {

    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");

    /*private Dialog dialog = new Dialog();*/
    private EventReservationRepository eventReservationRepository;
    private EventRepository eventRepository;
    private EventReservationService eventReservationService;
    private EventService eventService;
    private UserService userService;
    private UserRepository userRepository;
    private EventReservation eventReservation = new EventReservation();
    private BeanValidationBinder<EventReservation> binder = new BeanValidationBinder<>(EventReservation.class);
    private Event event;
    private H3 name;
    private Label description;
    private Label price;
    private String photourl;
    private Image img;

    @Autowired
    public EventView(EventReservationService eventReservationService, UserService userService, EventRepository eventRepository, EventService eventService) {

        //Pintar parte de registrar reserva
        FormLayout formLayout = new FormLayout();
        this.eventReservationService = eventReservationService;
        this.userService = userService;
        this.eventRepository = eventRepository;
        this.eventService = eventService;
        this.name = new H3();
        this.photourl = new String();
        this.description = new Label();
        this.price = new Label();
        this.img = new Image(""+photourl+"","hola");
        img.setHeight("100%");
        img.setWidth("250px");


        binder.bindInstanceFields(this);
        VerticalLayout verticalLayout1 = new VerticalLayout();
        VerticalLayout verticalLayout2 = new VerticalLayout();
        VerticalLayout verticalLayout3 = new VerticalLayout();
        firstName.setRequiredIndicatorVisible(true);
        lastName.setRequiredIndicatorVisible(true);


        Button register = new Button("Register", event -> {
            registerReservation();
        });
        Button cancel = new Button("Cancel",  event -> {
            UI.getCurrent().navigate(ServicesView.class);
        });


        register.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        register.addClickShortcut(Key.ENTER);
        cancel.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        HorizontalLayout buttons = new HorizontalLayout(register, cancel);
        verticalLayout1.setWidth("20%");
        verticalLayout2.setWidth("60%");
        verticalLayout3.setWidth("20%");
        verticalLayout1.add(name,description,price,firstName, lastName,buttons);
        verticalLayout3.add(img);
        add(verticalLayout3,verticalLayout1,verticalLayout2);

    }

    private void registerReservation() {
        eventReservation.setFirstName(firstName.getValue());
        eventReservation.setLastName(lastName.getValue());
        eventReservation.setUser(getUser());
        eventReservation.setServices("1");
        eventReservation.setDate(LocalDate.now().toString());
        eventReservation.setHour(LocalTime.now().toString());
        eventReservationService.create(eventReservation);
        UI.getCurrent().navigate(WelcomeView.class);
        UI.getCurrent().getPage().reload();
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Long id) {

        Location location = beforeEvent.getLocation();
        System.out.println(location.getSegments().get(1));
        id = Long.parseLong(location.getSegments().get(1));
        if(eventRepository.findById(id).isPresent()){
            eventReservation.setEvent(eventRepository.findById(id).get());
            event = eventService.findById(id).get();
            this.name.setText(event.getName());
            this.description.setText(event.getDescription());
            this.price.setText(""+event.getPrice()+"€");
            this.photourl = event.getPhoto();

        }

    }
}
