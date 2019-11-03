package es.uca.iw.sss.spring;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.polymertemplate.Id;
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
@Tag("header-footer")
@JsModule("./header-footer.js")
public class HeaderFooter extends PolymerTemplate<HeaderFooter.HeaderFooterModel> {
    @Id("header")
    private VerticalLayout header;

    @Id("content")
    private VerticalLayout content;

    @Id("footer")
    private VerticalLayout footer;
    /**
     * Creates a new HeaderFooter.
     */
    public HeaderFooter() {

    }

    /**
     * This model binds properties between HeaderFooter and header-footer
     */
    public interface HeaderFooterModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
