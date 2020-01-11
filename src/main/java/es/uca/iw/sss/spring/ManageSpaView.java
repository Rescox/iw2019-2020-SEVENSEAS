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

@Route(value = "ManageSpa", layout = MainLayout.class)
@PageTitle("Manage Spa")
public class ManageSpaView extends VerticalLayout implements HasUrlParameter<String> {
    final TextField filter;
    final Grid<Spa> spaGrid;
    private final SpaRepository spaRepository;
    private final SpaForm spaForm;
    private final Button addSpa;
    private  ShipService shipService;
    private Set<Spa> spas;

    public ManageSpaView(SpaRepository spaRepository, SpaForm spaForm, ShipService shipService)
    {
        this.spaRepository = spaRepository;
        this.shipService = shipService;
        this.spaForm = spaForm;
        this.spaGrid = new Grid<>(Spa.class);
        this.filter = new TextField();
        this.addSpa = new Button("New Spa", VaadinIcon.PLUS.create());

        HorizontalLayout actions = new HorizontalLayout(filter, addSpa);
        add(actions, spaGrid, spaForm);

        spaGrid.setColumns("id","name","description","aforum","photo","phone");
        filter.setPlaceholder("Filter by name");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listSpas(e.getValue()));

        spaGrid.asSingleSelect().addValueChangeListener(e -> {
            spaForm.editSpa(e.getValue());
        });
        addSpa.addClickListener(e -> spaForm.editSpa(new Spa()));
        spaForm.setChangeHandler(() -> {
            spaForm.setVisible(false);
            listSpas(filter.getValue());
        });
        listSpas(null);
    }

    void listSpas(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            spaGrid.setItems(spaRepository.findAll());
        }
        else {
            spaGrid.setItems(spaRepository.findByNameStartsWithIgnoreCase(filterText));
        }
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String licensePlate) {
        Location location = beforeEvent.getLocation();
        licensePlate = location.getSegments().get(1);
        spas = shipService.findByLicensePlate(licensePlate).getSpaSet();
        spaGrid.setItems(spas);
    }
}

