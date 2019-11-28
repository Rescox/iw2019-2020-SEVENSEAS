package es.uca.iw.sss.spring;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("Tours")
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
        menu = MainLayout.createMenuTabs();
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


