package es.uca.iw.sss.spring.ui.admin;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.*;
import es.uca.iw.sss.spring.backend.entities.AdviceCity;
import es.uca.iw.sss.spring.backend.repositories.AdviceCityRepository;
import es.uca.iw.sss.spring.backend.services.CityService;
import es.uca.iw.sss.spring.ui.common.MainLayout;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;

import java.util.Set;

@Secured("admin")
@Route(value = "ManageAdviceCity", layout = MainLayout.class)
@PageTitle("Manage Advice City")
public class ManageCityAdviceView extends VerticalLayout implements HasUrlParameter<Long> {
  final TextField filter;
  final Grid<AdviceCity> adviceGrid;
  private final AdviceCityRepository adviceRepository;
  private final AdviceCityForm adviceForm;
  private final Button addAdvice;
  private CityService cityService;
  private Set<AdviceCity> advices;

  public ManageCityAdviceView(
      AdviceCityRepository adviceRepository, AdviceCityForm adviceForm, CityService cityService) {
    this.adviceRepository = adviceRepository;
    this.adviceForm = adviceForm;
    this.adviceGrid = new Grid<>(AdviceCity.class);
    this.filter = new TextField();
    this.cityService = cityService;
    this.addAdvice = new Button("New Advice", VaadinIcon.PLUS.create());

    HorizontalLayout actions = new HorizontalLayout(filter, addAdvice);
    add(actions, adviceGrid, adviceForm);

    adviceGrid.setColumns("id", "advice", "city.id");
    adviceGrid.getColumnByKey("city.id").setHeader("Id City");
    filter.setPlaceholder("Filter by advice");
    filter.setValueChangeMode(ValueChangeMode.EAGER);
    filter.addValueChangeListener(e -> listAdvices(e.getValue()));

    adviceGrid
        .asSingleSelect()
        .addValueChangeListener(
            e -> {
              adviceForm.editAdvice(e.getValue());
            });
    addAdvice.addClickListener(e -> adviceForm.editAdvice(new AdviceCity()));
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
  public void setParameter(BeforeEvent beforeEvent, Long id) {
    Location location = beforeEvent.getLocation();
    id = Long.parseLong(location.getSegments().get(1));
    advices = cityService.findById(id).get().getAdviceSet();
    adviceGrid.setItems(advices);
  }
}
