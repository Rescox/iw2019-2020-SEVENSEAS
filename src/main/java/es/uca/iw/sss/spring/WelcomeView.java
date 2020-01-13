package es.uca.iw.sss.spring;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.sss.spring.meteorology.Weather;
import es.uca.iw.sss.spring.meteorology.WeatherService;
import es.uca.iw.sss.spring.utils.SecurityUtils;
import org.hibernate.dialect.function.TemplateRenderer;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Set;

@Route(value = "Welcome", layout = MainLayout.class)
@PageTitle("Welcome")
public class WelcomeView extends VerticalLayout {

    private final WeatherService weatherService;
    public WelcomeView(WeatherService weatherService)
    {
        this.weatherService = weatherService;
        if(SecurityUtils.hasRole("customer")) {
            VerticalLayout verticalLayout1 = new VerticalLayout();
            User currentUser = SecurityUtils.getUser();
            Ship currentShip = currentUser.getShip();
            Set<Scale> scaleSet = currentShip.getScaleSet();
            H2 shipMap = new H2("Map");
            Grid<Ship> gridShip = new Grid<>(Ship.class);
            Grid<Scale> gridScale = new Grid<>(Scale.class);
            gridShip.setColumns("name", "licensePlate");
            gridScale.setColumns("date", "city.name");
            gridScale.asSingleSelect().addValueChangeListener(e -> {
                this.scaleDetails(e.getValue());
            });

            gridScale.setItems(scaleSet);
            gridShip.setItems(currentShip);
            verticalLayout1.add(gridShip, gridScale);
            add(verticalLayout1);
        }
    }

    public void scaleDetails(Scale scale) {
        DecimalFormat df2 = new DecimalFormat("##.##");
        Weather weather =  weatherService.getWeather(scale.getCity().getName());
        double temp = weather.getTemperature() - 273.15;
        H2 temperature = new H2("Temperature in " + scale.getCity().getName() + " about " + df2.format(temp) + "ºC");
        H2 cloud = new H2("Clouds: " + weather.getWeatherDescription());
        H2 H2gallery = new H2("Gallery");
        VerticalLayout details = new VerticalLayout();
        VerticalLayout cityWeather = new VerticalLayout();
        FormLayout gallery = new FormLayout();
        gallery.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 2, FormLayout.ResponsiveStep.LabelsPosition.ASIDE));

        gallery.add(H2gallery);
        for(int i = 0; i < 7; i++) {
            gallery.add(new Image(scale.getCity().getPic() + "/img" + i +".jpeg", "asdasd"));
        }
        cityWeather.add(temperature, cloud);
        details.add(gallery, cityWeather);
        add(details);
    }
}
