package es.uca.iw.sss.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

@Route(value = "ManageEvent", layout = MainLayout.class)
@PageTitle("Manage Event")
public class ManageEventView extends VerticalLayout implements HasUrlParameter<String> {
  final TextField filter;
  final Grid<Event> eventGrid;
  private final EventRepository eventRepository;
  private final EventForm eventForm;
  private final Button addEvent;
  private ShipService shipService;
  private Set<Event> events;

  public ManageEventView(EventRepository eventRepository, EventForm eventForm, ShipService shipService) {
    this.eventRepository = eventRepository;
    this.shipService = shipService;
    this.eventForm = eventForm;
    this.eventGrid = new Grid<>(Event.class);
    this.filter = new TextField();
    this.addEvent = new Button("New Event", VaadinIcon.PLUS.create());

    HorizontalLayout actions = new HorizontalLayout(filter, addEvent);
    add(actions, eventGrid, eventForm);

    eventGrid.setColumns("id", "name", "description","aforum", "photo","init_time","end_time","price");
    filter.setPlaceholder("Filter by name");
    filter.setValueChangeMode(ValueChangeMode.EAGER);
    filter.addValueChangeListener(e -> listEvent(e.getValue()));

    eventGrid
        .asSingleSelect()
        .addValueChangeListener(
            e -> {
              eventForm.editEvent(e.getValue());
            });
    addEvent.addClickListener(e -> eventForm.editEvent(new Event()));
    eventForm.setChangeHandler(
        () -> {
          eventForm.setVisible(false);
          listEvent(filter.getValue());
        });
    listEvent(null);
  }

  void listEvent(String filterText) {
    if (StringUtils.isEmpty(filterText)) {
      eventGrid.setItems(eventRepository.findAll());
    } else {
      eventGrid.setItems(eventRepository.findByNameStartsWithIgnoreCase(filterText));
    }
  }

  @Override
  public void setParameter(BeforeEvent beforeEvent, String licensePlate) {
    Location location = beforeEvent.getLocation();
    licensePlate = location.getSegments().get(1);
    events = shipService.findByLicensePlate(licensePlate).getEventSet();
    eventGrid.setItems(events);
  }
}
