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
import com.vaadin.flow.component.html.Span;
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

@Route("register")
@PageTitle("Sign in")
public class RegisterForm  extends AppLayout {
    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private TextField DNI = new TextField("DNI");
    private EmailField email = new EmailField("Email");
    private PasswordField password = new PasswordField("Password");
    private TextField username = new TextField("Username");
    private Dialog dialog = new Dialog();
    private UserRepository userRepository;
    private UserService userService;
    private final Tabs menu;
    private User user = new User();
    private BeanValidationBinder<User> binder = new BeanValidationBinder<>(User.class);

    public RegisterForm(UserService userService) {

        menu = createMenuTabs();
        Span appName = new Span("Seven Seas Software");
        addToNavbar(appName);
        addToNavbar(true,menu);
        getElement().addEventListener("search-blur", e -> {
            getElement().getClassList().add("hide-navbar");
        });

       // Persistence.
        FormLayout formLayout = new FormLayout();
        this.userService = userService;
        VerticalLayout verticalLayout = new VerticalLayout();
        firstName.setRequiredIndicatorVisible(true);
        lastName.setRequiredIndicatorVisible(true);
        email.setRequiredIndicatorVisible(true);
        username.setRequiredIndicatorVisible(true);
        DNI.setRequiredIndicatorVisible(true);
        binder.bindInstanceFields(this);
        username.addValueChangeListener(e -> {
            if(userService.getUsers(username.getValue()) != null && user == null) {
                username.clear();
                Notification.show("User already taken", 2500, Notification.Position.MIDDLE);
            } else {
                if(userService.getUsers(username.getValue()) != null && user != null && !userService.getUsers(username.getValue()).getId().equals(user.getId())) {
                    username.clear();
                    Notification.show("User already taken", 2500, Notification.Position.MIDDLE);
                }
            }
        });
        email.addValueChangeListener(e -> {
            if(userService.getEmails(email.getValue()) != null && user == null) {
                email.clear();
                Notification.show("Email already registered", 2500, Notification.Position.MIDDLE);
            } else {
                if(userService.getEmails(email.getValue()) != null && user != null && !userService.getEmails(email.getValue()).getId().equals(user.getId())) {
                    email.clear();
                    Notification.show("Email already registered", 2500, Notification.Position.MIDDLE);
                }
            }
        });

        Button register = new Button("Sign in",  event -> {
            registerUser();
            dialog.close();
        });
        Button cancel = new Button("Cancel",  event -> {
            UI.getCurrent().navigate(LoginView.class);
            dialog.close();
        });
        dialog.add(register, cancel);
        register.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        register.addClickListener(e -> { dialog.open();
        });
        register.addClickShortcut(Key.ENTER);
        cancel.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        HorizontalLayout buttons = new HorizontalLayout(register, cancel);
        verticalLayout.add(firstName, lastName, DNI, email, username, password, buttons);
        formLayout.add(verticalLayout);
        setContent(formLayout);
    }

    private void registerUser() {
        binder.forField(email)
                // Explicit validator instance
                .withValidator(new EmailValidator(
                        "Invalid format. Example: user@XXX.XXX"))
                .bind(User::getEmail, User::setEmail);
        if(binder.validate().isOk()) {
            user.setUsername(username.getValue());
            user.setFirstName(firstName.getValue());
            user.setLastName(lastName.getValue());
            user.setDni(DNI.getValue());
            user.setEmail(email.getValue());
            user.setPassword(password.getValue());
            userService.create(user);
            UI.getCurrent().navigate(LoginView.class);
            // Faltará luego meter lo del email
        }
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
