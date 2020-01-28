package es.uca.iw.sss.spring.ui.costumer;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import es.uca.iw.sss.spring.backend.entities.Scale;
import es.uca.iw.sss.spring.backend.services.ScaleService;
import es.uca.iw.sss.spring.meteorology.Weather;
import es.uca.iw.sss.spring.meteorology.WeatherService;
import es.uca.iw.sss.spring.ui.common.MainLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import java.text.DecimalFormat;
@Secured("customer")
@Route(value = "scaleDetail", layout = MainLayout.class)
@PageTitle("ScaleDetail")
public class ScaleDetails extends VerticalLayout implements HasUrlParameter<Long> {
    private WeatherService weatherService;
    private final FormLayout gallery = new FormLayout();
    private final VerticalLayout cityWeather = new VerticalLayout();
    private final Image[] image = new Image[8];
    private Scale scale = null;
    private ScaleService scaleService;


    @Autowired
    public ScaleDetails(ScaleService scaleService, WeatherService weatherService) {
        this.scaleService = scaleService;
        this.weatherService = weatherService;
    }

    @Override
    public void setParameter(BeforeEvent event, Long id) {
        Location location = event.getLocation();
        id = Long.parseLong(location.getSegments().get(1));
        if (scaleService.findById(id).isPresent())
            scale = scaleService.findById(id).get();
        DecimalFormat df2 = new DecimalFormat("##.##");
        Weather weather = weatherService.getWeather(scale.getCity().getName());
        double temp = weather.getTemperature() - 273.15;
        H2 temperature =
                new H2("Temperature in " + scale.getCity().getName() + " about " + df2.format(temp) + "ºC");
        H2 cloud = new H2("Clouds: " + weather.getWeatherDescription());
        H2 H2gallery = new H2("Gallery");
        VerticalLayout details = new VerticalLayout();
        VerticalLayout cityWeather = new VerticalLayout();
        VerticalLayout galleryLayout = new VerticalLayout();
        FormLayout gallery = new FormLayout();
        gallery.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 2, FormLayout.ResponsiveStep.LabelsPosition.ASIDE));

        gallery.add(H2gallery);
        for (int i = 0; i < 7; i++) {
            gallery.add(new Image(scale.getCity().getPic() + "/img" + i + ".jpeg", "asdasd"));
        }
        cityWeather.add(temperature, cloud);

        VerticalLayout button = new VerticalLayout();
        Button returnbutton =
                new Button(
                        "Atrás",
                        e -> {
                            UI.getCurrent().navigate(WelcomeView.class);
                        });
        button.add(returnbutton);
        galleryLayout.add(gallery);
        details.add(H2gallery, galleryLayout, cityWeather);
        add(details, button);
    }
}