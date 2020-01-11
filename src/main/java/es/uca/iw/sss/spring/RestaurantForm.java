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
public class RestaurantForm extends VerticalLayout implements KeyNotifier {
    private final RestaurantRepository restaurantRepository;
    private Restaurant restaurant;
    private TextField aforum = new TextField("Aforum");
    private TextField photo = new TextField("Photo");
    private TextField description = new TextField("Description");
    private TextField name = new TextField("Name");
    private TextField licensePlate = new TextField("Ship License Plate");
    private RestaurantService restaurantService;
    private ShipService shipService;
    Button save = new Button("Save", VaadinIcon.CHECK.create());
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);
    private ChangeHandler changeHandler;

    @Autowired
    public RestaurantForm(RestaurantRepository restaurantRepository, RestaurantService restaurantService, ShipService shipService) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantService = restaurantService;
        this.shipService = shipService;
        add(name,description,aforum,photo,licensePlate,actions);

        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editRestaurant(restaurant));
        setVisible(false);
    }

    void delete() {
        restaurantRepository.delete(restaurant);
        changeHandler.onChange();
    }

    void save() {
        restaurant.setAforum(Long.parseLong(aforum.getValue()));
        restaurant.setPhoto(photo.getValue());
        restaurant.setName(name.getValue());
        restaurant.setDescription(description.getValue());
        restaurant.setShip(shipService.findByLicensePlate(licensePlate.getValue()));
        restaurantService.create(restaurant);
        changeHandler.onChange();
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editRestaurant(Restaurant restaurantEdit) {
        if (restaurantEdit == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = restaurantEdit.getId() != null;
        if (persisted) {
            restaurant = restaurantRepository.findById(restaurantEdit.getId()).get();
        }
        else {
            restaurant = restaurantEdit;
        }
        cancel.setVisible(persisted);

        if(restaurant.getAforum() != null)
        {
            aforum.setValue(Long.toString(restaurant.getAforum()));
        }
        else
        {
            aforum.setValue("");
        }
        if(restaurant.getPhoto() != null)
        {
            photo.setValue(restaurant.getPhoto());
        }
        else
        {
            photo.setValue("");
        }
        if(restaurant.getName() != null)
        {
            name.setValue(restaurant.getName());
        }
        else
        {
            name.setValue("");
        }
        if(restaurant.getDescription() != null)
        {
            description.setValue(restaurant.getDescription());
        }
        else
        {
            description.setValue("");
        }
        if(restaurant.getShip() != null)
        {
            licensePlate.setValue(Long.toString(restaurant.getShip().getId()));
        }
        else
        {
            licensePlate.setValue("");
        }

        setVisible(true);

        name.focus();
    }

    public void setChangeHandler(ChangeHandler h) {
        changeHandler = h;
    }

}