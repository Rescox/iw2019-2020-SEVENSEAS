package es.uca.iw.sss.spring;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;


@Route
@Tag("register")
@PageTitle("Register")
public class RegisterForm  extends AppLayout {
    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private TextField email = new TextField("Email");
    private PasswordField password = new PasswordField("Password");
    private Button register = new Button("Register");
    private Button delete = new Button("Delete");
    public RegisterForm() {
       // Persistence.
        FormLayout formLayout = new FormLayout();
        VerticalLayout verticalLayout = new VerticalLayout();
        firstName.setRequiredIndicatorVisible(true);
        lastName.setRequiredIndicatorVisible(true);
        email.setRequiredIndicatorVisible(true);
        HorizontalLayout buttons = new HorizontalLayout(register, delete);
        register.addThemeVariants(ButtonVariant.LUMO_CONTRAST);

        verticalLayout.add(firstName, lastName, email, password, buttons);
        formLayout.add(verticalLayout);
        setContent(formLayout);
    }
}
