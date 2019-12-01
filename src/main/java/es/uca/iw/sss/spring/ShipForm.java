package es.uca.iw.sss.spring;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("shipform")
@PageTitle("Ship registration")
public class ShipForm  extends AppLayout {
    private TextField licensePlate = new TextField("License Plate");
    private TextField name = new TextField("Name");
    private TextField plane = new TextField("Plane");
    private Dialog dialog = new Dialog();
    private ShipRepository shipRepository;
    private ShipService shipService;
    private Ship ship = new Ship();
    private BeanValidationBinder<Ship> binder = new BeanValidationBinder<>(Ship.class);

    public ShipForm(ShipService shipService) {

        FormLayout formLayout = new FormLayout();
        this.shipService = shipService;
        VerticalLayout verticalLayout = new VerticalLayout();
        licensePlate.setRequiredIndicatorVisible(true);
        name.setRequiredIndicatorVisible(true);
        plane.setRequiredIndicatorVisible(true);
        binder.bindInstanceFields(this);
        licensePlate.addValueChangeListener(e -> {
            if(shipService.getLicensePlate(licensePlate.getValue()) != null && ship == null) {
                licensePlate.clear();
                Notification.show("Ship already registered", 2500, Notification.Position.MIDDLE);
            } else {
                if(shipService.getLicensePlate(licensePlate.getValue()) != null && ship != null && !shipService.getLicensePlate(licensePlate.getValue()).getId().equals(ship.getId())) {
                    licensePlate.clear();
                    Notification.show("Ship already registered", 2500, Notification.Position.MIDDLE);
                }
            }
        });
        Button register = new Button("Sign in",  event -> {
            registerShip();
            dialog.close();
        });
        Button cancel = new Button("Cancel",  event -> {
            UI.getCurrent().navigate(WelcomeView.class);
            dialog.close();
        });
        dialog.add(register, cancel);
        register.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        register.addClickListener(e -> { dialog.open();
        });
        register.addClickShortcut(Key.ENTER);
        cancel.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        HorizontalLayout buttons = new HorizontalLayout(register, cancel);
        verticalLayout.add(licensePlate, name, plane, buttons);
        formLayout.add(verticalLayout);
        setContent(formLayout);
    }

    private void registerShip() {
            ship.setLicensePlate(licensePlate.getValue());
            ship.setName(name.getValue());
            ship.setPlane(plane.getValue());
            shipService.create(ship);
            UI.getCurrent().navigate(WelcomeView.class);
            UI.getCurrent().getPage().reload();

    }

    private static Tab createTab(Component content) {
        final Tab tab = new Tab();
        tab.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
        tab.add(content);
        return tab;
    }

    private static Tab createTab(VaadinIcon icon, String title, Class<? extends Component> viewClass) {
        return createTab(populateLink(new RouterLink(null, viewClass), icon, title));
    }

    private static <T extends HasComponents> T populateLink(T a, VaadinIcon icon, String title) {
        a.add(icon.create());
        a.add(title);
        return a;
    }

    private static Tabs createMenuTabs() {
        final Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.HORIZONTAL);
        tabs.add(createTab(VaadinIcon.EDIT, "Sign in",
                RegisterForm.class));
        return tabs;
    }
}
