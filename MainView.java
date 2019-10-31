package es.uca.iw.ss.spring;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

@Route
@PageTitle("Login")
@HtmlImport("frontend://bower_components/iron-form/iron-form.html") //
/**
 * A Designer generated component for the log-in template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("log-in")
@JsModule("./log-in.js")
public class MainView extends PolymerTemplate<MainView.LogInModel> {

    /**
     * Creates a new LogIn.
     */
    public MainView() {
        // You can initialise any data required for the connected UI components here.
    }

    /**
     * This model binds properties between LogIn and log-in
     */
    public interface LogInModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
