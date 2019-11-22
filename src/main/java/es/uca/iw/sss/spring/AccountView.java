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

@Route
@PageTitle("Welcome")
public class AccountView extends AppLayout {

    private final ConfirmDialog confirmDialog = new ConfirmDialog();
    private UserService service;
    private Grid<User> grid = new Grid<>(User.class);
    private final Tabs menu;

    @Autowired
    public AccountView(UserService userService) {
        service = userService;
        H1 DatosPersonales = new H1("DatosPersonales");
        H1 Gastos = new H1("Gastos");
        
        H1 Galeria = new H1("Galeria");
        menu = createMenuTabs();
        addToNavbar(true, menu);

        Span appName = new Span("Seven Seas Software");
        addToNavbar(appName);
        addToNavbar(true, menu);

        getElement().appendChild(confirmDialog.getElement());
        getElement().addEventListener("search-focus", e -> {
            getElement().getClassList().add("hide-navbar");
        });
        VerticalLayout content = new VerticalLayout();
        HorizontalLayout HL1 = new HorizontalLayout();
        HL1.add(DatosPersonales, Gastos, Galeria);
        content.add(HL1);
        setContent(content);
        updateList();
        getElement().addEventListener("search-blur", e -> {
            getElement().getClassList().add("hide-navbar");
        });
    }

    private static Tabs createMenuTabs() {
        final Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.HORIZONTAL);
        tabs.add(createTab(VaadinIcon.DOCTOR, "Welcome", MainView.class));
        tabs.add(createTab(VaadinIcon.DOCTOR, "Restaurants", RestaurantView.class));
        tabs.add(createTab(VaadinIcon.DOCTOR, "Tours", TourView.class));
        tabs.add(createTab(VaadinIcon.DOCTOR, "Tours", AccountView.class));
        return tabs;
    }

    private static Tab createTab(Component content) {
        final Tab tab = new Tab();
        tab.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);

        tab.add(content);
        return tab;
    }

    private static Tab createTab(VaadinIcon icon, String title, Class<? extends Component> viewClass) {
        return createTab(populateLink(new RouterLink(null, viewClass), icon, title));
    }

    private static <T extends HasComponents> T populateLink(T a, VaadinIcon icon, String title) {
        a.add(icon.create());
        a.add(title);
        return a;
    }

    public void updateList() {
        grid.setItems(service.findAll());
    }

}


