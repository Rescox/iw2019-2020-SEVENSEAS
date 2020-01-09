package es.uca.iw.sss.spring;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
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
public class ScaleForm extends VerticalLayout implements KeyNotifier {
    private final ScaleRepository scaleRepository;
    private Scale scale;
    private TextField city = new TextField("city");
    private TextField date = new TextField("date");
    private BeanValidationBinder<Scale> binder = new BeanValidationBinder<>(Scale.class);
    private ScaleService scaleService;
    private ShipService shipService;
    Button save = new Button("Save", VaadinIcon.CHECK.create());
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);
    private ChangeHandler changeHandler;

    @Autowired
    public ScaleForm(ScaleRepository scaleRepository, ScaleService scaleService, ShipService shipService) {
        this.scaleRepository = scaleRepository;
        this.scaleService = scaleService;
        this.shipService = shipService;
        add(city,date,actions);

        binder.bindInstanceFields(this);
        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editScale(scale));
        setVisible(false);
    }

    void delete() {
        scaleRepository.delete(scale);
        changeHandler.onChange();
    }

    void save() {
        scaleRepository.save(scale);
        changeHandler.onChange();
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editScale(Scale scaleEdit) {
        if (scaleEdit == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = scaleEdit.getId() != null;
        if (persisted) {
            scale = scaleRepository.findById(scaleEdit.getId()).get();
        }
        else {
            scale = scaleEdit;
        }
        cancel.setVisible(persisted);

        binder.setBean(scale);

        setVisible(true);

        city.focus();
    }

    public void setChangeHandler(ChangeHandler h) {
        changeHandler = h;
    }

}