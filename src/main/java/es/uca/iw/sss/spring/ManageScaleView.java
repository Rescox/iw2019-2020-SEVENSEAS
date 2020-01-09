package es.uca.iw.sss.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;

@Route(value = "ManageScale", layout = MainLayout.class)
@PageTitle("Manage Scale")
public class ManageScaleView extends VerticalLayout {
    final TextField filter;
    final Grid<Scale> scaleGrid;
    private final ScaleRepository scaleRepository;
    private final ScaleForm scaleForm;
    private final Button addScale;

    public ManageScaleView(ScaleRepository scaleRepository, ScaleForm scaleForm)
    {
        this.scaleRepository = scaleRepository;
        this.scaleForm = scaleForm;
        this.scaleGrid = new Grid<>(Scale.class);
        this.filter = new TextField();
        this.addScale = new Button("New scale", VaadinIcon.PLUS.create());

        HorizontalLayout actions = new HorizontalLayout(filter, addScale);
        add(actions, scaleGrid, scaleForm);

        scaleGrid.setColumns("id","entrance","departure","date");
        filter.setPlaceholder("Filter by entrance");
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
            scaleGrid.setItems(scaleRepository.findByEntranceStartsWithIgnoreCase(filterText));
        }
    }
}

