package es.uca.iw.sss.spring;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.Configuration;
import com.vaadin.flow.component.charts.model.ListSeries;
import com.vaadin.flow.component.charts.model.XAxis;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "WelcomeManager", layout = MainLayout.class)
public class ManagerWelcome extends VerticalLayout {
    private static final String[] MONTH_LABELS = new String[] {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
            "Aug", "Sep", "Oct", "Nov", "Dec"};
    public ManagerWelcome() {

        Chart chart = new Chart(ChartType.COLUMN);
        Configuration conf = chart.getConfiguration();
        conf.setTitle("HOla");
        ListSeries series = new ListSeries("Diameter");
        series.setData(4900,  12100,  12800,
                6800,  143000, 125000,
                51100, 49500);
        conf.addSeries(series);
        XAxis xaxis = new XAxis();
        xaxis.setCategories(MONTH_LABELS);
        xaxis.setTitle("Months");
        conf.addxAxis(xaxis);
        conf.setSubTitle("buenas tardes");
        add(chart);


    }
}
