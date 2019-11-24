package es.uca.iw.sss.spring;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;

@Route
@PageTitle("Welcome")
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainView extends AppLayout {

    private final ConfirmDialog confirmDialog = new ConfirmDialog();
    private UserService service;
    private Grid<User> grid = new Grid<>(User.class);
    private final Tabs menu;

    @Autowired
    public MainView(UserService userService) {
        service = userService;
        grid.setColumns("firstName", "lastName", "user");
        menu = createMenuTabs();

        Span appName = new Span("Seven Seas Software");
        addToNavbar(appName);
        addToNavbar(true, menu);

        getElement().appendChild(confirmDialog.getElement());
        getElement().addEventListener("search-focus", e -> {
            getElement().getClassList().add("hide-navbar");
        });
        VerticalLayout content = new VerticalLayout();
        content.add(grid);
        setContent(content);
        updateList();
        getElement().addEventListener("search-blur", e -> {
            getElement().getClassList().add("hide-navbar");
        });
    }

    public static Tabs createMenuTabs() {
        final Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.HORIZONTAL);
        tabs.add(createTab(VaadinIcon.DOCTOR, "Welcome", MainView.class));
        tabs.add(createTab(VaadinIcon.DOCTOR, "Restaurants", RestaurantView.class));
        tabs.add(createTab(VaadinIcon.DOCTOR, "Tours", TourView.class));
        tabs.add(createTab(VaadinIcon.DOCTOR, "MyAccount", AccountView.class));
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

    public void updateList() {
        grid.setItems(service.findAll());
    }

}


