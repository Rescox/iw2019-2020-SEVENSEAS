package es.uca.iw.sss.spring;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;

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
public class LoginView extends PolymerTemplate<LoginView.LogInModel> {

    /**
     * Creates a new LogIn.
     */
    public LoginView() {
        // You can initialise any data required for the connected UI components here.
    }

    /**
     * This model binds properties between LogIn and log-in
     */
    public interface LogInModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}