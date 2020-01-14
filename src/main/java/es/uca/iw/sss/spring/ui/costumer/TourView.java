package es.uca.iw.sss.spring;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.sss.spring.utils.SecurityUtils;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;

import com.vaadin.flow.component.button.Button;
import javafx.scene.control.DatePicker;
import org.apache.commons.lang3.StringUtils;

//import javax.management.Notification;
import javax.swing.*;
import javax.swing.text.Position;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Set;

@Route(value = "Tour", layout = MainLayout.class)
@PageTitle("Tours")
public class TourView<dialog> extends VerticalLayout {

    //private final Button makeReservation;
    private Grid<Tour> tourGrid = new Grid<>(Tour.class);
    private TourService tourService;
    private TextField filter;
    private final TourForm tourForm;
    private final TourRepository tourRepository;
    private final AccountService accountService;
    private Account account = new Account();
    private Tour[] tourSelected = new Tour[1];
    public TourView(TourService tourService, TourForm tourForm, TourRepository tourRepository, AccountService accountService){

        H3 header = new H3("Estos son los Tours con los que cuenta tu crucero.");
        this.tourService = tourService;
        this.tourForm = tourForm;
        this.tourRepository = tourRepository;
        this.accountService = accountService;
        this.filter = new TextField();
        User currentUser = SecurityUtils.getUser();
        Ship currentShip = currentUser.getShip();
        Set<Tour> tours = currentShip.getTourSet();
        VerticalLayout verticalLayout1 = new VerticalLayout();
        HorizontalLayout horizontalLayout = new HorizontalLayout();


        //Para que el grid tenga el tamaño de las columnas.
        tourGrid.setHeightByRows(true);
        tourGrid.setItems(tours);
        tourGrid.setColumns("name" , "price" , "schedule");

        //Un buscador que filtre
        filter.setPlaceholder("Filter by name");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listTours(e.getValue()));


        tourGrid.asSingleSelect().addValueChangeListener(e -> {
            tourForm.editTour(e.getValue());
            tourSelected[0] = e.getValue(); //Tour seleccionado
        });

        ////A partir de aquí se trata del botón que muestra los detalles.
        tourGrid.setSelectionMode(Grid.SelectionMode.NONE);
        tourGrid.setItemDetailsRenderer(TemplateRenderer.<Tour>of(
                "<div class='custom-details' style='border: 1px solid gray; padding: 20px; width: 100%; box-sizing: border-box;'>"
                        + "<div>Los detalles de este tour son: <b>[[item.description]]</b></div>"
                        + "</div>")
                .withProperty("description", Tour::getDescription)
                // Asi es como abrimos los detalles y la descripcion del tour.
                .withEventHandler("handleClick", tour -> {
                    tourGrid.getDataProvider().refreshItem(tour);
                }));

        tourForm.setChangeHandler(() -> {
            tourForm.setVisible(false);
            listTours(filter.getValue());
        });

        listTours(null);

        //Deshabilita la forma que tiene por defecto de mostrar los detalles:
        tourGrid.setDetailsVisibleOnClick(false);

        tourGrid.addColumn(new NativeButtonRenderer<>("Detalles", item -> tourGrid
                .setDetailsVisible(item, !tourGrid.isDetailsVisible(item)))).setHeader("More Details");
        ////Hasta aqui es el boton.

        //Voton de Reservar del Gird.
        tourGrid.addComponentColumn(item -> createReservationButton(tourGrid, item, account, accountService)).setHeader("Haz tu Reserva").setId("Reserve");

        tourGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);
        verticalLayout1.add(tourGrid);
        add(header,filter,verticalLayout1);
    }

    private Component createReservationButton(Grid<Tour> tourGrid, Tour item, Account account, AccountService accountService) {
        @SuppressWarnings("unchecked")
        Button button = new Button("Reservar", clickEvent -> {
            try {
                //Esta parte del código es para que cuando pulses el boton
                //de reservar, el botón de deshabilite durante 1,5seg.
                //Para evitar clickar repetidas veces mientras se guarda en la BD.
                Thread.sleep(1000);
                clickEvent.getSource().setEnabled(true);
            } catch (InterruptedException e) {
            }
            //Opciones para que el cuadro emergente no se cierre clickando fuera
            //o pulsando la tecla ESC.
            Dialog dialog = new Dialog();
            VerticalLayout mensajeEmergente = new VerticalLayout();
            dialog.setCloseOnEsc(false);
            dialog.setCloseOnOutsideClick(false);
            //Opciones del cuadro emergente o dialog.

            Button confirmButton = new Button("Confirmar", event -> {
                ListDataProvider<Tour> dataProvider = (ListDataProvider<Tour>) tourGrid
                        .getDataProvider();
                account.setServiceName(item.getName());
                account.setPrice(Double.valueOf(item.getPrice()));
                account.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                account.setOwner(SecurityUtils.getUser());
                accountService.saveAccount(account);
                //H3 message = new H3("¡Su reserva ha sido realizada!");
                Notification notification = new Notification(
                        "¡Reserva realizada con éxito!", 5000);
                notification.open();
                dialog.close();

            });


            Button cancelButton = new Button("Cancelar", event -> {
                //H3 message = new H3("Operacion de reserva cancelada");
                Notification notification = new Notification(
                        "Reserva cancelada...", 5000);
                notification.open();

                dialog.close();

            });

            dialog.add(confirmButton, cancelButton);
            dialog.open();


            //Fin del cuadro emergente o dialog.

        });

        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button.setDisableOnClick(true);
        return button;
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






