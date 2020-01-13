package es.uca.iw.sss.spring;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalTime;
import java.util.Date;
import java.util.Set;

import static es.uca.iw.sss.spring.utils.SecurityUtils.getUser;
@Route(value = "EventsView", layout = MainLayout.class)
@PageTitle("Events")
public class EventsView extends VerticalLayout{
    private EventService eventService;
    private EventRepository eventRepository;
    private ShipService shipService;
    private ShipRepository shipRepository;
    private UserService userService;


    public EventsView(EventService eventService) {

        //Datos
        User currentUser = getUser();
        Ship currentShip = currentUser.getShip();
        Set<Event> events = currentShip.getEventSet();

        //Layouts
        VerticalLayout verticalLayout1 = new VerticalLayout();
        verticalLayout1.setWidth("30%");

        //Mostrar todos los nombres de los eventos
        Date date = new Date();
        for(Event ev: events)
        {
            Button boton = new Button(ev.getName(), e -> SpasView(ev.getId()));
            verticalLayout1.add(boton);
        }

        add(verticalLayout1);
    }

    public void SpasView(Long id_event)
    {
        UI.getCurrent().navigate(EventView.class, id_event);
        UI.getCurrent().getPage().reload();
    }


}
