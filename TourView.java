package es.uca.iw.sss.spring;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.sss.spring.utils.SecurityUtils;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;

import com.vaadin.flow.component.button.Button;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Route(value = "Tour", layout = MainLayout.class)
@PageTitle("Tours")
public class TourView extends HorizontalLayout {

    //private final Button makeReservation;
    private Grid<Tour> tourGrid = new Grid<>(Tour.class);
    private TourService tourService;
    private TextField filter;
    private final TourForm tourForm;
    private final TourRepository tourRepository;
    private Tour[] tourSelected = new Tour[1];
    public TourView(TourService tourService, TourForm tourForm, TourRepository tourRepository){
        //this.makeReservation = new Button("Reservar", VaadinIcon.PLUS.create());
        //String str = "2016-03-04 11:30:40";
        //DateTimeFormatter formatter = DateTimeFormatter
        //        .ofPattern("yyyy-MM-dd HH:mm:ss");
        //LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        //LocalDate localDate = LocalDate.parse(str, formatter);
        //VerticalLayout parts = new VerticalLayout(makeReservation);
        //add(tourGrid,makeReservation);

        H3 header = new H3("Estos son los Tours con los que cuenta tu crucero.");
        this.tourService = tourService;
        this.tourForm = tourForm;
        this.tourRepository = tourRepository;
        this.filter = new TextField();
        User currentUser = SecurityUtils.getUser();
        Ship currentShip = currentUser.getShip();
        Set<Tour> tours = currentShip.getTourSet();
        VerticalLayout verticalLayout1 = new VerticalLayout();
        tourGrid.setItems(tours);
        tourGrid.setColumns("name" , "price" , "schedule");
        filter.setPlaceholder("Filter by name");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listTours(e.getValue()));


        tourGrid.asSingleSelect().addValueChangeListener(e -> {
            tourForm.editTour(e.getValue());
            tourSelected[0] = e.getValue(); //Tour seleccionado
            //advices.setEnabled(true);
        });
        //tourGrid.addColumn(Tour::getName).setHeader("Name").setKey("Name");
        //tourGrid.addColumn(Tour::getDescription).setHeader("Description").setKey("Description");
        //tourGrid.addColumn(Tour::getPrice).setHeader("Price").setKey("Price");
        //tourGrid.addColumn(Tour::getSchedule).setHeader("Schedule").setKey("Schedule");
        //A partir de aquí se trata del botón que muestra los detalles.
        tourGrid.setSelectionMode(Grid.SelectionMode.NONE);
        tourGrid.setItemDetailsRenderer(TemplateRenderer.<Tour>of(
                "<div class='custom-details' style='border: 1px solid gray; padding: 10px; width: 100%; box-sizing: border-box;'>"
                        + "<div>Los detalles de este tour son: <b>[[item.description]]</b></div>"
                        + "</div>")
                .withProperty("description", Tour::getDescription)
                // This is now how we open the details
                .withEventHandler("handleClick", tour -> {
                    tourGrid.getDataProvider().refreshItem(tour);
                }));

        tourGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);

        tourForm.setChangeHandler(() -> {
            tourForm.setVisible(false);
            listTours(filter.getValue());
        });

        listTours(null);

        // Disable the default way of opening item details:
        tourGrid.setDetailsVisibleOnClick(false);

        tourGrid.addColumn(new NativeButtonRenderer<>("Detalles", item -> tourGrid
                .setDetailsVisible(item, !tourGrid.isDetailsVisible(item))));
        //Hasta aqui es el boton.
        verticalLayout1.add(tourGrid);
        add(verticalLayout1);
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






