package es.uca.iw.sss.spring.ui.admin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.*;
import es.uca.iw.sss.spring.backend.entities.AdviceShip;
import es.uca.iw.sss.spring.backend.repositories.AdviceShipRepository;
import es.uca.iw.sss.spring.backend.services.ShipService;
import es.uca.iw.sss.spring.ui.common.MainLayout;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;

import java.util.Set;

@Secured("admin")
@Route(value = "ManageAdviceShip", layout = MainLayout.class)
@PageTitle("Manage Advice Ship")
public class ManageShipAdviceView extends VerticalLayout implements HasUrlParameter<String> {
  final TextField filter;
  final Grid<AdviceShip> adviceGrid;
  private final AdviceShipRepository adviceRepository;
  private final AdviceShipForm adviceForm;
  private final Button addAdvice;
  private ShipService shipService;
  private Set<AdviceShip> advices;

  public ManageShipAdviceView(
      AdviceShipRepository adviceRepository, AdviceShipForm adviceForm, ShipService shipService) {
    this.adviceRepository = adviceRepository;
    this.adviceForm = adviceForm;
    this.adviceGrid = new Grid<>(AdviceShip.class);
    this.filter = new TextField();
    this.shipService = shipService;
    this.addAdvice = new Button("New Advice", VaadinIcon.PLUS.create());

    HorizontalLayout actions = new HorizontalLayout(filter, addAdvice);
    add(actions, adviceGrid, adviceForm);

    adviceGrid.setColumns("id", "advice", "ship.licensePlate");
    adviceGrid.getColumnByKey("ship.licensePlate").setHeader("Ship License Plate");
    filter.setPlaceholder("Filter by advice");
    filter.setValueChangeMode(ValueChangeMode.EAGER);
    filter.addValueChangeListener(e -> listAdvices(e.getValue()));

    adviceGrid
        .asSingleSelect()
        .addValueChangeListener(
            e -> {
              adviceForm.editAdvice(e.getValue());
            });
    addAdvice.addClickListener(e -> adviceForm.editAdvice(new AdviceShip()));
    adviceForm.setChangeHandler(
        () -> {
          adviceForm.setVisible(false);
          listAdvices(filter.getValue());
        });
    listAdvices(null);
  }

  void listAdvices(String filterText) {
    if (StringUtils.isEmpty(filterText)) {
      adviceGrid.setItems(adviceRepository.findAll());
    } else {
      adviceGrid.setItems(adviceRepository.findByAdviceStartsWithIgnoreCase(filterText));
    }
  }

  @Override
  public void setParameter(BeforeEvent beforeEvent, String licensePlate) {
    Location location = beforeEvent.getLocation();
    licensePlate = location.getSegments().get(1);
    advices = shipService.findByLicensePlate(licensePlate).getAdviceSet();
    adviceGrid.setItems(advices);
  }
}
