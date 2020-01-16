package es.uca.iw.sss.spring.ui.costumer;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.sss.spring.backend.entities.Event;
import es.uca.iw.sss.spring.backend.entities.Ship;
import es.uca.iw.sss.spring.backend.entities.User;
import es.uca.iw.sss.spring.backend.repositories.EventRepository;
import es.uca.iw.sss.spring.backend.repositories.ShipRepository;
import es.uca.iw.sss.spring.backend.services.EventService;
import es.uca.iw.sss.spring.backend.services.ShipService;
import es.uca.iw.sss.spring.backend.services.UserService;
import es.uca.iw.sss.spring.ui.common.MainLayout;
import org.springframework.security.access.annotation.Secured;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import static es.uca.iw.sss.spring.utils.SecurityUtils.getUser;

@Secured("customer")
@Route(value = "EventsView", layout = MainLayout.class)
@PageTitle("Events")
public class EventsView extends VerticalLayout{
    private EventService eventService;
    private EventRepository eventRepository;
    private ShipService shipService;
    private ShipRepository shipRepository;
    private UserService userService;


    public EventsView(EventService eventService) throws ParseException {

        //Datos
        User currentUser = getUser();
        Ship currentShip = currentUser.getShip();
        Set<Event> events = currentShip.getEventSet();

        //Layouts
        VerticalLayout verticalLayout1 = new VerticalLayout();
        verticalLayout1.setWidth("30%");

        //Mostrar todos los nombres de los eventos
        for(Event ev: events)
        {
            String date = ev.getDate();
            Date parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            Date dateToday = new Date();
            if(dateToday.before(parsedDate))
            {
                VerticalLayout info = new VerticalLayout();
                Button boton = new Button("View more", e -> EventsView(ev.getId()));
                H3 nombre = new H3(ev.getName());
                Label descripcion = new Label(ev.getDescription());
                Label schenduled = new Label(ev.getDate()+" "+ev.getInit_time());
                info.add(nombre,descripcion,schenduled,boton);
                verticalLayout1.add(info);
            }
        }

        add(verticalLayout1);
    }

    public void EventsView(Long id_event)
    {
        UI.getCurrent().navigate(EventView.class, id_event);
        UI.getCurrent().getPage().reload();
    }


}
