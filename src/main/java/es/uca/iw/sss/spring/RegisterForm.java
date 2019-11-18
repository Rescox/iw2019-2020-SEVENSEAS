package es.uca.iw.sss.spring;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Route
@Tag("register")
@PageTitle("Register")
@Service
public class RegisterForm  extends AppLayout {
    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private TextField DNI = new TextField("DNI");
    private EmailField email = new EmailField("Email");
    private PasswordField password = new PasswordField("Password");
    private TextField username = new TextField("Username");
    private Button register = new Button("Register");
    private Button delete = new Button("Delete");
    private UserRepository userRepository;
    private UserService userService;
    User user = new User();
    Binder<User> binder = new Binder<>(User.class);
    BeanValidationBinder<User> binderClass = new BeanValidationBinder<>(User.class);


    public RegisterForm(UserService userService) {
       // Persistence.
        FormLayout formLayout = new FormLayout();
        this.userService = userService;
        VerticalLayout verticalLayout = new VerticalLayout();
        firstName.setRequiredIndicatorVisible(true);
        lastName.setRequiredIndicatorVisible(true);
        email.setRequiredIndicatorVisible(true);
        username.setRequiredIndicatorVisible(true);
        DNI.setRequiredIndicatorVisible(true);
        HorizontalLayout buttons = new HorizontalLayout(register, delete);
        register.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        register.addClickListener(e -> { registerUser(user);
        });
        verticalLayout.add(firstName, lastName, DNI, email, username, password, buttons);
        formLayout.add(verticalLayout);
        setContent(formLayout);
    }

    private void registerUser(User user) {
        System.out.println(firstName.getValue());
        System.out.println(lastName.getValue());
        System.out.println(DNI.getValue());
        System.out.println(email.getValue());
        System.out.println(username.getValue());

        user.setFirstName(firstName.getValue());
        user.setLastName(lastName.getValue());
        user.setDni(DNI.getValue());
        user.setEmail(email.getValue());
        user.setUsername(username.getValue());
        user.setPassword(password.getValue());
        userService.create(user);
    }
}
