package es.uca.iw.sss.spring;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.model.Buttons;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;
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

@Route("tours")
@PageTitle("Tours")
public class TourView extends AppLayout {

    private final ConfirmDialog confirmDialog = new ConfirmDialog();
    private UserService service;
    private Grid<User> grid = new Grid<>(User.class);
    private final Tabs menu;

    @Autowired
    public TourView(UserService userService) {
        service = userService;
        H1 prueba = new H1("Tours");
        menu = MainView.createMenuTabs();
        addToNavbar(true, menu);

        Span appName = new Span("Seven Seas Software");
        addToNavbar(appName);
        addToNavbar(true, menu);

        getElement().appendChild(confirmDialog.getElement());
        getElement().addEventListener("search-focus", e -> {
            getElement().getClassList().add("hide-navbar");
        });
        VerticalLayout content = new VerticalLayout();
        content.add(prueba);
        setContent(content);
        getElement().addEventListener("search-blur", e -> {
            getElement().getClassList().add("hide-navbar");
        });
    }


}


