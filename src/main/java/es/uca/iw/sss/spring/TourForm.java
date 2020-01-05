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
public class TourForm extends VerticalLayout implements KeyNotifier {
    private final TourRepository tourRepository;
    private Tour tour;
    private TextField name = new TextField("Name");
    private TextField description = new TextField("Description");
    private TextField price = new TextField("Price");
    private TextField schedule = new TextField("Schedule");
    private BeanValidationBinder<Tour> binder = new BeanValidationBinder<>(Tour.class);
    private TourService tourService;
    Button save = new Button("Save", VaadinIcon.CHECK.create());
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    private ChangeHandler changeHandler;

    @Autowired
    public TourForm(TourRepository tourRepository, TourService tourService) {
        this.tourRepository = tourRepository;
        this.tourService = tourService;
        add(name,description,price,schedule,actions);

        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editTour(tour));
        setVisible(false);
    }

    void delete() {
        tourRepository.delete(tour);
        changeHandler.onChange();
    }

    void save() {
        tour.setName(name.getValue());
        tour.setPrice(Float.parseFloat(price.getValue()));
        tour.setDescription(description.getValue());
        tour.setSchedule(schedule.getValue());
        tourService.create(tour);
        changeHandler.onChange();
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editTour(Tour tourEdit) {
        if (tourEdit == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = tourEdit.getId() != null;
        if (persisted) {
            tour = tourRepository.findById(tourEdit.getId()).get();
        }
        else {
            tour = tourEdit;
        }
        cancel.setVisible(persisted);

        binder.setBean(tour);

        setVisible(true);

        name.focus();
    }

    public void setChangeHandler(ChangeHandler h) {
        changeHandler = h;
    }

}