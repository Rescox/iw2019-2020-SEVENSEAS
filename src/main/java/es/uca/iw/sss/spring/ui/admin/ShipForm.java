package es.uca.iw.sss.spring.ui.admin;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import es.uca.iw.sss.spring.backend.entities.Ship;
import es.uca.iw.sss.spring.backend.repositories.ShipRepository;
import es.uca.iw.sss.spring.backend.services.ShipService;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class ShipForm extends VerticalLayout implements KeyNotifier {
    private final ShipRepository shipRepository;
    private Ship ship;
    private TextField name = new TextField("Name");
    private TextField plane = new TextField("Plane");
    private TextField licensePlate = new TextField("License Plate");
    private TextField legend = new TextField("Legend");
    private ShipService shipService;
    private Button save = new Button("Save", VaadinIcon.CHECK.create());
    private Button cancel = new Button("Reset");
    private Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    private HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);
    private ChangeHandler changeHandler;

    @Autowired
    public ShipForm(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
        shipService = ShipService.getInstance(shipRepository);
        add(name,licensePlate,plane,legend,actions);

        licensePlate.addValueChangeListener(e -> {
            if(shipService.getLicensePlate(licensePlate.getValue()) != null && ship == null) {
                Notification.show("Ship already registered", 2500, Notification.Position.MIDDLE);
            } else {
                if(shipService.getLicensePlate(licensePlate.getValue()) != null && ship != null && !shipService.getLicensePlate(licensePlate.getValue()).getId().equals(ship.getId())) {
                    Notification.show("Ship already registered", 2500, Notification.Position.MIDDLE);
                }
            }
        });
        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editShip(ship));
        setVisible(false);
    }

    private void delete() {
        shipRepository.delete(ship);
        changeHandler.onChange();
    }

    private void save() {
        shipRepository.save(ship);
        changeHandler.onChange();
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editShip(Ship shipEdit) {
        if (shipEdit == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = shipEdit.getId() != null;
        if (persisted) {
            ship = shipRepository.findById(shipEdit.getId()).get();
        } else {
            ship = shipEdit;
        }

        if (ship.getName() != null) {
            name.setValue(ship.getName());
        } else {
            name.setValue("");
        }
        if (ship.getLicensePlate() != null) {
            licensePlate.setValue(ship.getLicensePlate());
        } else {
            licensePlate.setValue("");
        }
        if (ship.getPlane() != null) {
            plane.setValue(ship.getPlane());
        } else {
            plane.setValue("");
        }
        if (ship.getLegend() != null) {
            legend.setValue(ship.getLegend());
        } else {
            legend.setValue("");
        }

        cancel.setVisible(persisted);

        setVisible(true);

        name.focus();
    }

    public void setChangeHandler(ChangeHandler h) {
        changeHandler = h;
    }

}