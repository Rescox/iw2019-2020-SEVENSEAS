package es.uca.iw.sss.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

@Route(value = "ManageScale", layout = MainLayout.class)
@PageTitle("Manage Scale")
public class ManageScaleView extends VerticalLayout implements HasUrlParameter<String> {
    final TextField filter;
    final Grid<Scale> scaleGrid;
    private final ScaleRepository scaleRepository;
    private final ScaleForm scaleForm;
    private final Button addScale;
    private final ShipService shipService;
    private Set<Scale> scales;

    public ManageScaleView(ScaleRepository scaleRepository, ScaleForm scaleForm,ShipService shipService)
    {
        this.scaleRepository = scaleRepository;
        this.shipService = shipService;
        this.scaleForm = scaleForm;
        this.scaleGrid = new Grid<>(Scale.class);
        this.filter = new TextField();
        this.addScale = new Button("New Scale", VaadinIcon.PLUS.create());

        HorizontalLayout actions = new HorizontalLayout(filter, addScale);
        add(actions, scaleGrid, scaleForm);

        scaleGrid.setColumns("id","date","city.id","ship.id");
        scaleGrid.getColumnByKey("city.id").setHeader("Id City");
        scaleGrid.getColumnByKey("ship.id").setHeader("Id Ship");

        filter.setPlaceholder("Filter by date");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listScales(e.getValue()));

        scaleGrid.asSingleSelect().addValueChangeListener(e -> {
            scaleForm.editScale(e.getValue());
        });
        addScale.addClickListener(e -> scaleForm.editScale(new Scale()));
        scaleForm.setChangeHandler(() -> {
            scaleForm.setVisible(false);
            listScales(filter.getValue());
        });
        listScales(null);
    }

    void listScales(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            scaleGrid.setItems(scaleRepository.findAll());
        }
        else {
            scaleGrid.setItems(scaleRepository.findByDateStartsWithIgnoreCase(filterText));
        }
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String licensePlate) {
        Location location = beforeEvent.getLocation();
        licensePlate = location.getSegments().get(1);
        scales = shipService.findByLicensePlate(licensePlate).getScaleSet();
        scaleGrid.setItems(scales);
    }
}

