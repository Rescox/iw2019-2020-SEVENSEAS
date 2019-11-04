package es.uca.iw.sss.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;

/**
 * A Designer generated component for the header-footer template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Route
@PageTitle("headerfooter")
@Tag("header-footer")
@JsModule("./header-footer.js")
public class HeaderFooter extends PolymerTemplate<HeaderFooter.HeaderFooterModel> implements TemplateModel {
    @Id("header")
    private HorizontalLayout header;

    @Id("login")
    private Button loginbutton;

    @Id("content")
    private VerticalLayout content;

    @Id("footer")
    private HorizontalLayout footer;
    /**
     * Creates a new HeaderFooter.
     */
    public HeaderFooter() {
        loginbutton.addClickListener(e -> {
            loginbutton.getUI().ifPresent(ui ->
                    ui.navigate("login"));
        });
    }

    /**
     * This model binds properties between HeaderFooter and header-footer
     */
    public interface HeaderFooterModel extends TemplateModel {

    }
}
