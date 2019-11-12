package es.uca.iw.sss.spring;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.*;
import com.vaadin.flow.templatemodel.TemplateModel;
import es.uca.iw.sss.spring.SevenSeas.ViewPort;

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
@Viewport(ViewPort.VIEWPORT)
public class LoginView extends VerticalLayout {

    /**
     * Creates a new LogIn.
     */
    public LoginView() {
    LoginOverlay login = new LoginOverlay();
    login.setOpened(true);
    LoginI18n I18n = LoginI18n.createDefault();
    I18n.setHeader(new LoginI18n.Header());
    I18n.getHeader().setTitle("Seven Seas");
    I18n.getHeader().setDescription("Introduce your email and password");
    I18n.setForm(new LoginI18n.Form());
    I18n.getForm().setSubmit("Sign in");
    I18n.getForm().setTitle("Sign in");
    I18n.getForm().setUsername("Email");
    I18n.getForm().setPassword("Password");
    login.setI18n(I18n);
    login.setForgotPasswordButtonVisible(false);
    login.setAction("login");
    add(login);

    }
}