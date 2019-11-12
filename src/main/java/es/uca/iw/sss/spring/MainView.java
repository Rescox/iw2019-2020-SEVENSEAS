package es.uca.iw.sss.spring;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.model.Buttons;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Route
@PageTitle("Welcome")

@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainView extends AppLayout {

    private final ConfirmDialog confirmDialog = new ConfirmDialog();
    private CustomerService service = CustomerService.getInstance();
    private Grid<Customer> grid = new Grid<>(Customer.class);

    public MainView() {
        grid.setColumns("firstName", "lastName", "user");
        Span appName = new Span("Seven Seas Software");
        Button tab1 = new Button("Login");
        tab1.addClickListener(e -> tab1.getUI().ifPresent(ui -> ui.navigate("login")));
        Button tab2 = new Button("Register");
        tab2.addClickListener(e -> tab2.getUI().ifPresent(ui -> ui.navigate("registerform")));
        this.addToNavbar(appName);
        this.addToNavbar(tab1, tab2);
        this.getElement().appendChild(confirmDialog.getElement());
        getElement().addEventListener("search-focus", e -> {
            getElement().getClassList().add("hide-navbar");
        });
        VerticalLayout content = new VerticalLayout();
        content.add(grid);
        setContent(content);
        getElement().addEventListener("search-blur", e -> {
            getElement().getClassList().add("hide-navbar");
        });
    }


}


