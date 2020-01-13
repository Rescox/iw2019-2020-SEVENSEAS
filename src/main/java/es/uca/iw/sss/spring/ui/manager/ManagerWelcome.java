package es.uca.iw.sss.spring.ui.manager;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.Configuration;
import com.vaadin.flow.component.charts.model.ListSeries;
import com.vaadin.flow.component.charts.model.XAxis;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import es.uca.iw.sss.spring.backend.entities.Event;
import es.uca.iw.sss.spring.backend.entities.Ship;
import es.uca.iw.sss.spring.backend.entities.Tour;
import es.uca.iw.sss.spring.backend.repositories.ShipRepository;
import es.uca.iw.sss.spring.ui.common.MainLayout;

@Route(value = "WelcomeManager", layout = MainLayout.class)
public class ManagerWelcome extends VerticalLayout {
    private static final String[] MONTH_LABELS = new String[] {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
            "Aug", "Sep", "Oct", "Nov", "Dec"};
    private ShipRepository shipRepository;
    private Grid shipGrid;
    private Ship ship;

    public ManagerWelcome(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
        this.shipGrid = new Grid<>(Ship.class);
        shipGrid.setItems(shipRepository.findAll());
        shipGrid.setColumns("id","name","licensePlate");
        shipGrid.asSingleSelect().addValueChangeListener(e -> {
            ManagerWelcome.addChart((Ship) e.getValue(), this);
             ship = (Ship) e.getValue(); // Barco seleccionado
        });
        add(shipGrid);
    }

    public static void addChart(Ship ship, ManagerWelcome managerWelcome) {
        Chart chartFacturation = new Chart(ChartType.SPLINE);
        Chart chartTours = new Chart(ChartType.COLUMN);
        Chart chartEvents = new Chart(ChartType.COLUMN);

        Configuration conf = chartFacturation.getConfiguration();
        conf.setTitle(ship.getName());
        ListSeries series = new ListSeries("Spent");
        series.setData(4900,  12100,  12800,
                6800,  143000, 125000,
                51100, 49500);
        Number[] money = series.getData();
        float totalMoney = 0;
        for(Number i : money) {
            totalMoney += i.floatValue();
        }
        H1 facturation = new H1("El número total de facturación este año es de: " + totalMoney + "€");
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
        series1.setData(5,  2,  3
                );
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
        series2.setData(50,  20,  30);
        size = ship.getTourSet().size();
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


        managerWelcome.add(chartFacturation, facturation, chartTours, chartEvents);
    }
}
