package es.uca.iw.sss.spring;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;

/**
 * A Designer generated component for the reg-form template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("reg-form")
@JsModule("./reg-form.js")
public class RegForm extends PolymerTemplate<RegForm.RegFormModel> {

    @Id("vaadinVerticalLayout")
    private VerticalLayout vaadinVerticalLayout;

    /**
     * Creates a new RegForm.
     */
    public RegForm() {
        // You can initialise any data required for the connected UI components here.
    }

    /**
     * This model binds properties between RegForm and reg-form
     */
    public interface RegFormModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
