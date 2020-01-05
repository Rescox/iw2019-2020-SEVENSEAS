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

@Route(value = "ManageTour", layout = MainLayout.class)
@PageTitle("Manage Tour")
public class ManageTourView extends VerticalLayout {
    final TextField filter;
    final Grid<Tour> tourGrid;
    private final TourRepository tourRepository;
    private final TourForm tourForm;
    private final Button addTour;

    public ManageTourView(TourRepository tourRepository, TourForm tourForm)
    {
        this.tourRepository = tourRepository;
        this.tourForm = tourForm;
        this.tourGrid = new Grid<>(Tour.class);
        this.filter = new TextField();
        this.addTour = new Button("New tour", VaadinIcon.PLUS.create());

        HorizontalLayout actions = new HorizontalLayout(filter, addTour);
        add(actions, tourGrid, tourForm);

        tourGrid.setColumns("id","name","description","price","schedule");
        filter.setPlaceholder("Filter by name");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listTours(e.getValue()));

        tourGrid.asSingleSelect().addValueChangeListener(e -> {
            tourForm.editTour(e.getValue());
        });
        addTour.addClickListener(e -> tourForm.editTour(new Tour()));
        tourForm.setChangeHandler(() -> {
            tourForm.setVisible(false);
            listTours(filter.getValue());
        });
        listTours(null);
    }

    void listTours(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            tourGrid.setItems(tourRepository.findAll());
        }
        else {
            tourGrid.setItems(tourRepository.findByNameStartsWithIgnoreCase(filterText));
        }
    }
}

