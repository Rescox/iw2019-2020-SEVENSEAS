package es.uca.iw.sss.spring;


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
        Chart chart = new Chart(ChartType.SPLINE);
        Configuration conf = chart.getConfiguration();
        conf.setTitle(ship.getName());
        ListSeries series = new ListSeries("Diameter");
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
        managerWelcome.add(chart, facturation);
    }
}
