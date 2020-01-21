package es.uca.iw.sss.spring;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.datepicker.DatePicker;
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
import org.springframework.beans.factory.annotation.Autowired;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


import static es.uca.iw.sss.spring.utils.SecurityUtils.getUser;

@Route(value = "SpaView", layout = MainLayout.class)
@PageTitle("SpaView")
public class SpaView extends HorizontalLayout implements HasUrlParameter<Long> {

    //private TextField firstName = new TextField("First name");
    //private TextField lastName = new TextField("Last name");
    private DatePicker datePicker;
    private NumberField numberField;
    private LocalDate now = LocalDate.now();
    private TimePicker timePicker;

    private SpaReservationRepository spaReservationRepository;
    private SpaRepository spaRepository;
    private SpaReservationService spaReservationService;
    private SpaService spaService;
    private UserService userService;
    private UserRepository userRepository;
    private SpaReservation spaReservation = new SpaReservation();
    private BeanValidationBinder<SpaReservation> binder = new BeanValidationBinder<>(SpaReservation.class);
    private Spa spa;
    private H3 name;
    private Label description;
    private Label price;
    private String photourl;
    private Image img;
    private ConfirmDialog dialog;

    @Autowired
    public SpaView(SpaReservationService spaReservationService, UserService userService, SpaRepository spaRepository, SpaService spaService, SpaReservationRepository spaReservationRepository) {


        datePicker = new DatePicker();
        timePicker = new TimePicker();
        numberField = new NumberField();
        this.spaReservationService = spaReservationService;
        this.spaReservationRepository = spaReservationRepository;
        this.userService = userService;
        this.spaRepository = spaRepository;
        this.spaService = spaService;
        this.name = new H3();
        this.photourl = new String();
        this.description = new Label();
        this.price = new Label();
        this.img = new Image();
        this.dialog = new ConfirmDialog();
        img.setHeight("620px");
        img.setWidth("700px");

        //binder.bindInstanceFields(this);
        VerticalLayout verticalLayout1 = new VerticalLayout();
        VerticalLayout verticalLayout2 = new VerticalLayout();
        //firstName.setRequiredIndicatorVisible(true);
        //lastName.setRequiredIndicatorVisible(true);
        datePicker.setLabel("Choose a day");
        datePicker.setMin(now.withDayOfMonth(1));
        datePicker.setMax(now.withDayOfMonth(now.lengthOfMonth()));
        datePicker.setRequired(true);
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
            UI.getCurrent().navigate(EventsView.class);
        });


        register.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        register.addClickShortcut(Key.ENTER);
        cancel.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        HorizontalLayout buttons = new HorizontalLayout(register, cancel);
        verticalLayout1.setWidth("40%");
        verticalLayout2.setWidth("60%");
        verticalLayout1.add(name,description,price, datePicker,timePicker,numberField,buttons);
        verticalLayout2.add(img);
        add(verticalLayout1,verticalLayout2);

    }

    private void registrar() {
        if(timePicker.getValue().toString() != "" && datePicker.getValue().toString() != "" && validDate())
        {
            dialog.open();
        }
    }

    private void onCancel(ConfirmDialog.CancelEvent cancelEvent) {
        UI.getCurrent().navigate(SpasView.class);
    }

    private boolean validDate(){

        String date = datePicker.getValue().toString();
        Date parsedDate = null;
        try {
            parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date dateToday = new Date();
        if(dateToday.before(parsedDate))
        {
            return true;
        }else return false;
    }

    private boolean enoughAforum() {

        String dateHour1 = spaReservation.getDate() +" "+spaReservation.getHour();
        Date parsedDateHour1 = null;
        try {
            parsedDateHour1 = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(dateHour1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<SpaReservation> reservations = spaReservationRepository.findBySpa(spa);
        Long cont = 0L;
        for(SpaReservation r: reservations)
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

        if(cont + spaReservation.getPersons() > spa.getAforum())
        {
            return false;
        }
        else return true;

    }

    private void onPublish(ConfirmDialog.ConfirmEvent confirmEvent){
        spaReservation.setFirstName(getUser().getFirstName());
        spaReservation.setLastName(getUser().getLastName());
        spaReservation.setUser(getUser());
        spaReservation.setServices("1");
        spaReservation.setDate(datePicker.getValue().toString());
        spaReservation.setPrice(numberField.getValue().floatValue() * spa.getPrice() );
        spaReservation.setHour(timePicker.getValue().toString());
        spaReservation.setPersons(numberField.getValue().longValue());
        if(enoughAforum())
        {
            spaReservationService.create(spaReservation);
            UI.getCurrent().navigate(WelcomeView.class);
            UI.getCurrent().getPage().reload();
        }
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Long id) {

        Location location = beforeEvent.getLocation();
        System.out.println(location.getSegments().get(1));
        id = Long.parseLong(location.getSegments().get(1));
        if(spaRepository.findById(id).isPresent()){
            spaReservation.setSpa(spaRepository.findById(id).get());
            spa = spaService.findById(id).get();
            this.name.setText(spa.getName());
            this.description.setText(spa.getDescription());
            this.price.setText(""+spa.getPrice()+"€ per person");
            this.photourl = spa.getPhoto();
            this.img.setSrc(this.photourl);
            this.img.setAlt(this.photourl);
            dialog.setConfirmButton("Yes",this::onPublish);
            dialog.setCancelButton("No", this::onCancel);

        }

    }

}
