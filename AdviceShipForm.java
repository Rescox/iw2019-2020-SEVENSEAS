package es.uca.iw.sss.spring;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class AdviceShipForm extends VerticalLayout implements KeyNotifier {
    private final AdviceShipRepository adviceShipRepository;
    private AdviceShip advices;
    private TextField advice = new TextField("AdviceShip");
    private TextField shipLicensePlate = new TextField("Ship license plate");
    private BeanValidationBinder<AdviceShip> binder = new BeanValidationBinder<>(AdviceShip.class);
    private AdviceShipService adviceShipService;
    private ShipService shipService;
    Button save = new Button("Save", VaadinIcon.CHECK.create());
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);
    private ChangeHandler changeHandler;

    @Autowired
    public AdviceShipForm(AdviceShipRepository adviceRepository, AdviceShipService adviceShipService, ShipService shipService) {
        this.adviceShipRepository = adviceRepository;
        this.adviceShipService = adviceShipService;
        this.shipService = shipService;
        add(advice,shipLicensePlate,actions);

        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editAdvice(advices));
        setVisible(false);
    }

    void delete() {
        adviceShipRepository.delete(advices);
        changeHandler.onChange();
    }

    void save() {
        advices.setAdvice(advice.getValue());
        advices.setShip(shipService.findByLicensePlate(shipLicensePlate.getValue()));
        adviceShipService.create(advices);
        changeHandler.onChange();
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editAdvice(AdviceShip adviceShipEdit) {
        if (adviceShipEdit == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = adviceShipEdit.getId() != null;
        if (persisted) {
            advices = adviceShipRepository.findById(adviceShipEdit.getId()).get();
        }
        else {
            advices = adviceShipEdit;
        }
        cancel.setVisible(persisted);

        binder.setBean(advices);

        setVisible(true);

        advice.focus();
    }

    public void setChangeHandler(ChangeHandler h) {
        changeHandler = h;
    }

}