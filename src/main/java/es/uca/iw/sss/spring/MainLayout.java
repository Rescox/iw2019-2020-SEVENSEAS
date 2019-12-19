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
import com.vaadin.flow.component.html.*;
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
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static es.uca.iw.sss.spring.utils.SecurityUtils.getUser;

@PageTitle("Welcome")
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainLayout extends AppLayout {

    private final ConfirmDialog confirmDialog = new ConfirmDialog();
    private UserService service;
    private ShipService shipService;

    private final Tabs menu;

    @Autowired
    public MainLayout(UserService userService, ShipService shiipService) {
        service = userService;
        shipService = shiipService;
        User loggedUser = getUser();
        H3 nombreapellido = new H3 ("Bienvenido, " + getUser().toString());
        menu = createMenuTabs();
        Button logout = new Button("Logout");
        logout.addClickListener(e -> {logout();});

        Span appName = new Span("Seven Seas Software");
        addToNavbar(appName);
        addToNavbar(true, nombreapellido, menu, logout);
    }

    private void logout() {
        VaadinSession.getCurrent().getSession().invalidate();
        UI.getCurrent().navigate(LoginView.class);
        UI.getCurrent().getPage().reload();
    }

    public static Tabs createMenuTabs() {
        final Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.HORIZONTAL);
        tabs.add(createTab(VaadinIcon.HANDSHAKE, "Welcome", WelcomeView.class));
        tabs.add(createTab(VaadinIcon.FLAG, "Advices", AdvicesView.class));
        tabs.add(createTab(VaadinIcon.HEART, "Services", ServicesView.class));
        tabs.add(createTab(VaadinIcon.TICKET, "Tours", TourView.class));
        tabs.add(createTab(VaadinIcon.USER, "MyAccount", AccountView.class));
        return tabs;
    }

    public static Tab createTab(Component content) {
        final Tab tab = new Tab();
        tab.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
        tab.add(content);
        return tab;
    }

    public static Tab createTab(VaadinIcon icon, String title, Class<? extends Component> viewClass) {
        return createTab(populateLink(new RouterLink(null, viewClass), icon, title));
    }

    public static <T extends HasComponents> T populateLink(T a, VaadinIcon icon, String title) {
        a.add(icon.create());
        a.add(title);
        return a;
    }

}


