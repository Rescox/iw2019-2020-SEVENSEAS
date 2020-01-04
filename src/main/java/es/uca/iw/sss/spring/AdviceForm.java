package es.uca.iw.sss.spring;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class AdviceForm extends VerticalLayout implements KeyNotifier {
    private final AdviceRepository adviceRepository;
    private Advice advices;
    private TextField advice = new TextField("Advice");
    private TextField shipLicensePlate = new TextField("Ship license plate");
    private BeanValidationBinder<Advice> binder = new BeanValidationBinder<>(Advice.class);
    private AdviceService adviceService;
    private ShipService shipService;
    Button save = new Button("Save", VaadinIcon.CHECK.create());
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);
    private ChangeHandler changeHandler;

    @Autowired
    public AdviceForm(AdviceRepository adviceRepository, AdviceService adviceService, ShipService shipService) {
        this.adviceRepository = adviceRepository;
        this.adviceService = adviceService;
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
        adviceRepository.delete(advices);
        changeHandler.onChange();
    }

    void save() {
        advices.setAdvice(advice.getValue());
        advices.setShip(shipService.findByLicensePlate(shipLicensePlate.getValue()));
        adviceService.create(advices);
        changeHandler.onChange();
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editAdvice(Advice adviceEdit) {
        if (adviceEdit == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = adviceEdit.getId() != null;
        if (persisted) {
            advices = adviceRepository.findById(adviceEdit.getId()).get();
        }
        else {
            advices = adviceEdit;
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