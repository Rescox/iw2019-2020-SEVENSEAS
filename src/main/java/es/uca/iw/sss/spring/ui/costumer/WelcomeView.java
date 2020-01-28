package es.uca.iw.sss.spring.ui.costumer;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.sss.spring.backend.entities.Scale;
import es.uca.iw.sss.spring.backend.entities.Ship;
import es.uca.iw.sss.spring.backend.entities.User;
import es.uca.iw.sss.spring.meteorology.Weather;
import es.uca.iw.sss.spring.meteorology.WeatherService;
import es.uca.iw.sss.spring.ui.common.MainLayout;
import es.uca.iw.sss.spring.utils.SecurityUtils;
import org.springframework.security.access.annotation.Secured;

import java.text.DecimalFormat;
import java.util.Set;

@Secured("customer")
@Route(value = "Welcome", layout = MainLayout.class)
@PageTitle("Welcome")
public class WelcomeView extends VerticalLayout {

  private final WeatherService weatherService;
  private final VerticalLayout scaleDetail = new VerticalLayout();

  public WelcomeView(WeatherService weatherService) {
    this.weatherService = weatherService;
    if (SecurityUtils.hasRole("customer")) {
      VerticalLayout verticalLayout1 = new VerticalLayout();
      User currentUser = SecurityUtils.getUser();
      Ship currentShip = currentUser.getShip();
      Set<Scale> scaleSet = currentShip.getScaleSet();
      H2 shipName = new H2("Ship name: " + currentShip.getName());
      H2 shipLicense = new H2("Ship license: " + currentShip.getLicensePlate());
      Grid<Scale> gridScale = new Grid<>(Scale.class);
      gridScale.setColumns("date", "city.name");



      gridScale
          .asSingleSelect()
          .addValueChangeListener(
              e -> scaleDetails(e.getValue()));

      gridScale.setItems(scaleSet);
      verticalLayout1.add(shipName, shipLicense, gridScale);
      if(getComponentCount() > 2)
          remove(verticalLayout1);
      add(verticalLayout1, scaleDetail);
    }
  }

  public void scaleDetails(Scale scale) {
    Long id_scale = scale.getId();
    UI.getCurrent().navigate(ScaleDetails.class, id_scale);

  }
}
