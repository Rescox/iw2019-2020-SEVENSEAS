package es.uca.iw.sss.spring;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.*;
import com.vaadin.flow.templatemodel.TemplateModel;


@HtmlImport("frontend://bower_components/iron-form/iron-form.html") //
/**
 * A Designer generated component for the log-in template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Route
@Tag("login")
@PageTitle("Login")
@JsModule("./styles/shared-styles.js")
public class LoginView extends LoginOverlay  implements BeforeEnterObserver, AfterNavigationObserver{

    /**
     * Creates a new LogIn.
     */
    public LoginView() {

    LoginI18n I18n = LoginI18n.createDefault();
    I18n.setHeader(new LoginI18n.Header());
    I18n.getHeader().setTitle("Seven Seas");
    I18n.getHeader().setDescription("Introduce your email and password");
    I18n.setForm(new LoginI18n.Form());
    I18n.getForm().setSubmit("Sign in");
    I18n.getForm().setTitle("Sign in");
    I18n.getForm().setUsername("Email");
    I18n.getForm().setPassword("Password");
    setI18n(I18n);
    setForgotPasswordButtonVisible(false);
    setAction("login");

    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {

    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        setError(
                event.getLocation().getQueryParameters().getParameters().containsKey(
                        "error"));
    }
}