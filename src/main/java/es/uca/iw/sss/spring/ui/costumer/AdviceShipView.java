package es.uca.iw.sss.spring.ui.costumer;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.sss.spring.backend.entities.AdviceShip;
import es.uca.iw.sss.spring.backend.entities.Ship;
import es.uca.iw.sss.spring.backend.entities.User;
import es.uca.iw.sss.spring.backend.services.AdviceShipService;
import es.uca.iw.sss.spring.ui.common.MainLayout;
import es.uca.iw.sss.spring.utils.SecurityUtils;
import org.springframework.security.access.annotation.Secured;
import java.util.Set;

@Secured("customer")
@Route(value = "AdviceShip", layout = MainLayout.class)
@PageTitle("AdviceShip")
public class AdviceShipView extends FormLayout {
  private Grid<AdviceShip> adviceGrid = new Grid<>(AdviceShip.class);
  private AdviceShipService adviceService;

  public AdviceShipView(AdviceShipService adviceService) {
    this.adviceService = adviceService;
    User currentUser = SecurityUtils.getUser();
    Ship currentShip = currentUser.getShip();
    Set<AdviceShip> advices = currentShip.getAdviceSet();
    VerticalLayout verticalLayout1 = new VerticalLayout();
    adviceGrid.setColumns("advice");
    adviceGrid.setItems(advices);
    verticalLayout1.add(adviceGrid);
    add(verticalLayout1);
  }
}
