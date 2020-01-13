package es.uca.iw.sss.spring;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class RegisterForm extends VerticalLayout implements KeyNotifier {
    private final UserRepository userRepository;
    private User user;
    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private TextField DNI = new TextField("DNI");
    private TextField userName = new TextField("Username");
    private TextField shipLicensePlate = new TextField("Ship license plate");
    private TextField role = new TextField("Role");
    private EmailField email = new EmailField("Email");
    private PasswordField password = new PasswordField("Password");
    private BeanValidationBinder<User> binder = new BeanValidationBinder<>(User.class);
    private UserService userService;
    private ShipService shipService;
    private String pass;
    Button save = new Button("Save", VaadinIcon.CHECK.create());
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    private ChangeHandler changeHandler;

    @Autowired
    public RegisterForm(UserRepository userRepository,UserService userService, ShipService shipService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.shipService = shipService;
        add(firstName,lastName,DNI,userName,email,password,role,shipLicensePlate,actions);

        binder.bindInstanceFields(this);
        userName.addValueChangeListener(e -> {
            if(userService.getUsers(userName.getValue()) != null && user == null) {
                Notification.show("User already taken", 2500, Notification.Position.MIDDLE);
            } else {
                if(userService.getUsers(userName.getValue()) != null && user != null && !userService.getUsers(userName.getValue()).getId().equals(user.getId())) {
                    Notification.show("User already taken", 2500, Notification.Position.MIDDLE);
                }
            }
        });
        email.addValueChangeListener(e -> {
            if(userService.getEmails(email.getValue()) != null && user == null) {
                Notification.show("Email already registered", 2500, Notification.Position.MIDDLE);
            } else {
                if(userService.getEmails(email.getValue()) != null && user != null && !userService.getEmails(email.getValue()).getId().equals(user.getId())) {
                    Notification.show("Email already registered", 2500, Notification.Position.MIDDLE);
                }
            }
        });
        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());
        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editUser(user));
        setVisible(false);
    }

    void delete() {
        userRepository.delete(user);
        changeHandler.onChange();
    }

    void save() {
        user.setShip(shipService.findByLicensePlate(shipLicensePlate.getValue()));
        userService.create(user);
        if(!this.pass.isEmpty() && password.getValue().length() == 60)
        {
            user.setPassword(this.pass);
            userService.saveUser(user);
        }
        changeHandler.onChange();

    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editUser(User userEdit) {
        if (userEdit == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = userEdit.getId() != null;
        if (persisted) {
            user = userRepository.findById(userEdit.getId()).get();
        }
        else {
            user = userEdit;
        }
        cancel.setVisible(persisted);

        binder.setBean(user);

        if(user.getShip() != null)
        {
            shipLicensePlate.setValue(user.getShip().getLicensePlate());
        }
        else
        {
            shipLicensePlate.setValue("");
        }

        pass = user.getPassword();

        setVisible(true);

        firstName.focus();
    }

    public void setChangeHandler(ChangeHandler h) {
        changeHandler = h;
    }

}