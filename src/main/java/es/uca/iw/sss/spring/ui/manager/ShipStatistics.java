package es.uca.iw.sss.spring.ui.manager;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.Configuration;
import com.vaadin.flow.component.charts.model.ListSeries;
import com.vaadin.flow.component.charts.model.XAxis;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import es.uca.iw.sss.spring.backend.entities.*;
import es.uca.iw.sss.spring.backend.services.*;
import es.uca.iw.sss.spring.ui.common.MainLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Secured("manager")
@Route(value = "shipStatistics", layout = MainLayout.class)
@PageTitle("ShipStatistics")
public class ShipStatistics extends VerticalLayout implements HasUrlParameter<Long> {

    private static final String[] MONTH_LABELS = new String[] {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
            "Aug", "Sep", "Oct", "Nov", "Dec"};
    private ShipService shipService;
    private AccountService accountService;
    private EventReservationService eventReservationService;
    private SpaReservationService spaReservationService;
    private Ship ship;


    @Autowired
    public ShipStatistics(ShipService shipService, AccountService accountService, EventReservationService eventReservationService, SpaReservationService spaReservationService) {
        this.shipService = shipService;
        this.accountService = accountService;
        this.eventReservationService = eventReservationService;
        this.spaReservationService = spaReservationService;
    }
    @Override
    public void setParameter(BeforeEvent beforeEvent, Long id) {
        Location location = beforeEvent.getLocation();
        id = Long.parseLong(location.getSegments().get(1));
        if (shipService.findById(id) != null)
            ship = shipService.findById(id);
        Chart chartFacturation = new Chart(ChartType.SPLINE);
        Chart chartTours = new Chart(ChartType.COLUMN);
        Chart chartEvents = new Chart(ChartType.COLUMN);
        Chart chartSpas = new Chart(ChartType.COLUMN);
        H2 h2Facturation = new H2("Facturación anual: ");
        H2 h2Eventos = new H2("Eventos reservados: ");
        H2 h2SpasRestaurantes = new H2("Spas reservados: ");
        H2 h2Tours = new H2("Tours reservados: ");
        Button returnbutton =
                new Button(
                        "Atrás",
                        e -> {
                            UI.getCurrent().navigate(ManagerWelcome.class);
                        });




        Configuration conf = chartFacturation.getConfiguration();
        conf.setTitle(ship.getName());
        ListSeries series = new ListSeries("Spent");
        Number[] money = series.getData();
        Set<User> users = ship.getUserSet();
        List<Account> accounts;
        float[] moneyTotalArray = new float[12];
        Number[] numberTotalArray = new Number[12];
        float moneyTotal = 0;
        Iterator<User> itUser = users.iterator();
        for(int cont = 0; cont < users.size(); cont++) {
            int cont2;
            accounts = accountService.findByUser(itUser.next());
            for(cont2 = 0; cont2 < accounts.size(); cont2++) {
                moneyTotalArray[accounts.get(cont2).getDate().getMonth()] += accounts.get(cont2).getPrice();
            }
        }

        for(float f : moneyTotalArray) {
            moneyTotal += f;
        }

        for(int cont3 = 0; cont3 < moneyTotalArray.length; cont3++) {
            numberTotalArray[cont3] = moneyTotalArray[cont3];
        }
        series.setData(numberTotalArray);
        H1 facturation = new H1("El número total de facturación este año es de: " + moneyTotal  +"€");
        conf.addSeries(series);
        XAxis xaxis = new XAxis();
        xaxis.setCategories(MONTH_LABELS);
        xaxis.setTitle("Months");
        conf.addxAxis(xaxis);
        conf.setSubTitle(ship.getLicensePlate());
        //-------------------------------------TOURS--------------------------------------------
        Configuration conf1 = chartTours.getConfiguration();
        conf1.setTitle(ship.getName());
        ListSeries series1 = new ListSeries("People");
        Number[] tours = new Number[ship.getTourSet().size()];
        Set<Tour> setTour = ship.getTourSet();
        Iterator<Tour> itTour = setTour.iterator();
        for(int i = 0; i < ship.getTourSet().size(); i++) {
            tours[i] = accountService.countByServiceName(itTour.next().getName());
        }
        series1.setData(tours);
        int size = ship.getTourSet().size();
        String[] tourName = new String[size];
        int i = 0;
        for(Tour tour : ship.getTourSet()) {
            tourName[i] = tour.getName();
            i++;
        }
        XAxis xaxis1 = new XAxis();
        xaxis1.setCategories(tourName);
        xaxis1.setTitle("Tours name");
        conf1.addSeries(series1);
        conf1.addxAxis(xaxis1);
        conf1.setSubTitle(ship.getLicensePlate());

        //--------------------------EVENTS------------------------
        Configuration conf2 = chartEvents.getConfiguration();
        conf2.setTitle(ship.getName());
        ListSeries series2 = new ListSeries("Request");
        size = ship.getTourSet().size();

        Number[] events = new Number[ship.getTourSet().size()];
        Set<Event> setEvent = ship.getEventSet();
        Iterator<Event> itEvent = setEvent.iterator();
        for(i = 0; i < ship.getEventSet().size(); i++) {
            events[i] = eventReservationService.countByEvent(itEvent.next());
        }
        series2.setData(events);
        String[] eventName = new String[size];
        i = 0;
        for(Event event : ship.getEventSet()) {
            eventName[i] = event.getName();
            i++;
        }
        XAxis xaxis2 = new XAxis();
        xaxis2.setCategories(eventName);
        xaxis2.setTitle("Events name");
        conf2.addSeries(series2);
        conf2.addxAxis(xaxis2);
        conf2.setSubTitle(ship.getLicensePlate());

        //----------------------------------SPAS----------------------------
        Configuration conf3 = chartSpas.getConfiguration();
        conf3.setTitle(ship.getName());
        ListSeries series3 = new ListSeries("Request");


        Number[] spas = new Number[ship.getSpaSet().size()];
        Set<Spa> setSpa = ship.getSpaSet();
        Iterator<Spa> itSpa = setSpa.iterator();
        for(i = 0; i < ship.getSpaSet().size(); i++) {
            spas[i] = spaReservationService.countBySpa(itSpa.next());
        }
        series3.setData(spas);

        String[] spaName = new String[ship.getSpaSet().size()];
        i = 0;
        for(Spa spa : ship.getSpaSet()) {
            spaName[i] = spa.getName();
            i++;
        }
        XAxis xaxis3 = new XAxis();
        xaxis3.setCategories(spaName);
        xaxis3.setTitle("Spas name");
        conf3.addSeries(series3);
        conf3.addxAxis(xaxis3);
        conf3.setSubTitle(ship.getLicensePlate());

        add(returnbutton, h2Facturation, chartFacturation, facturation,h2Tours, chartTours,h2Eventos, chartEvents,h2SpasRestaurantes, chartSpas);

    }
}
