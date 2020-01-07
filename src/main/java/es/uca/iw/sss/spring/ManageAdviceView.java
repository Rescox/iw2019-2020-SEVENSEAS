package es.uca.iw.sss.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@Route(value = "ManageAdvice", layout = MainLayout.class)
@PageTitle("Manage Advice")
public class ManageAdviceView extends VerticalLayout implements HasUrlParameter<String> {
    final TextField filter;
    final Grid<Advice> adviceGrid;
    private final AdviceRepository adviceRepository;
    private final AdviceForm adviceForm;
    private final Button addAdvice;
    private String licensePlate;
    private ShipService shipService;
    private Set<Advice> advices;

    public ManageAdviceView (AdviceRepository adviceRepository, AdviceForm adviceForm, ShipService shipService)
    {
        this.adviceRepository = adviceRepository;
        this.adviceForm = adviceForm;
        this.adviceGrid = new Grid<>(Advice.class);
        this.filter = new TextField();
        this.shipService = shipService;
        this.addAdvice = new Button("New advice", VaadinIcon.PLUS.create());

        HorizontalLayout actions = new HorizontalLayout(filter, addAdvice);
        add(actions, adviceGrid, adviceForm);

        adviceGrid.setColumns("id","advice");
        filter.setPlaceholder("Filter by advice");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listAdvices(e.getValue()));

        adviceGrid.asSingleSelect().addValueChangeListener(e -> {
            adviceForm.editAdvice(e.getValue());
        });
        addAdvice.addClickListener(e -> adviceForm.editAdvice(new Advice()));
        adviceForm.setChangeHandler(() -> {
            adviceForm.setVisible(false);
            listAdvices(filter.getValue());
        });
        listAdvices(null);
    }

    void listAdvices(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            adviceGrid.setItems(adviceRepository.findAll());
        }
        else {
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

