package es.uca.iw.sss.spring.ui.costumer;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.*;
import es.uca.iw.sss.spring.backend.entities.Event;
import es.uca.iw.sss.spring.backend.entities.EventReservation;
import es.uca.iw.sss.spring.backend.repositories.EventRepository;
import es.uca.iw.sss.spring.backend.repositories.EventReservationRepository;
import es.uca.iw.sss.spring.backend.repositories.UserRepository;
import es.uca.iw.sss.spring.backend.services.EventReservationService;
import es.uca.iw.sss.spring.backend.services.EventService;
import es.uca.iw.sss.spring.backend.services.UserService;
import es.uca.iw.sss.spring.ui.common.MainLayout;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;

import static es.uca.iw.sss.spring.utils.SecurityUtils.getUser;

@Route(value = "EventView", layout = MainLayout.class)
@PageTitle("Event")
public class EventView extends HorizontalLayout implements HasUrlParameter<Long> {

    //private TextField firstName = new TextField("First name");
    //private TextField lastName = new TextField("Last name");
    private NumberField numberField;

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
    private Label schenduled;
    private Label price;
    private String photourl;
    private Image img;
    private ConfirmDialog dialog;

    @Autowired
    public EventView(EventReservationService eventReservationService, UserService userService, EventRepository eventRepository, EventService eventService, EventReservationRepository eventReservationRepository) {

        this.eventReservationService = eventReservationService;
        this.userService = userService;
        this.eventRepository = eventRepository;
        this.eventService = eventService;
        this.eventReservationRepository = eventReservationRepository;
        this.name = new H3();
        this.photourl = new String();
        this.description = new Label();
        this.schenduled = new Label();
        this.price = new Label();
        this.numberField = new NumberField();
        this.img = new Image();
        this.dialog = new ConfirmDialog();
        img.setHeight("620px");
        img.setWidth("700px");

        //binder.bindInstanceFields(this);
        VerticalLayout verticalLayout1 = new VerticalLayout();
        VerticalLayout verticalLayout2 = new VerticalLayout();
        //firstName.setRequiredIndicatorVisible(true);
        //lastName.setRequiredIndicatorVisible(true);
        numberField.setValue(1d);
        numberField.setHasControls(true);
        numberField.setMin(1);
        numberField.setLabel("Number of persons");


        Button register = new Button("Register", event -> {
            registrar();
        });
        Button cancel = new Button("Cancel",  event -> {
            UI.getCurrent().navigate(EventsView.class);
        });


        register.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        register.addClickShortcut(Key.ENTER);
        cancel.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        HorizontalLayout buttons = new HorizontalLayout(register, cancel);
        verticalLayout1.setWidth("40%");
        verticalLayout2.setWidth("60%");
        verticalLayout1.add(name,description,schenduled,price,numberField,buttons);
        verticalLayout2.add(img);
        add(verticalLayout1,verticalLayout2);

    }

    private void registrar() {

            dialog.open();

    }


    private void onCancel(ConfirmDialog.CancelEvent cancelEvent) {
        UI.getCurrent().navigate(EventView.class);
    }

    private void onPublish(ConfirmDialog.ConfirmEvent confirmEvent) {
            eventReservation.setFirstName(getUser().getFirstName());
            eventReservation.setLastName(getUser().getLastName());
            eventReservation.setUser(getUser());
            eventReservation.setServices("1");
            eventReservation.setDate(LocalDate.now().toString());
            eventReservation.setHour(LocalTime.now().toString());
            eventReservation.setPersons(numberField.getValue().longValue());
            eventReservation.setPrice(event.getPrice()*eventReservation.getPersons());
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
            this.schenduled.setText(event.getDate()+" "+event.getInit_time()+" to "+event.getEnd_time());
            this.price.setText(""+event.getPrice()+"€ per person");
            this.photourl = event.getPhoto();
            this.img.setSrc(this.photourl);
            this.img.setAlt(this.photourl);
            dialog.setHeader("Confirm reservation");
            dialog.setText("The final import will be added to your account");
            dialog.setConfirmButton("Yes",this::onPublish);
            dialog.setCancelButton("No", this::onCancel);

        }

    }
}
