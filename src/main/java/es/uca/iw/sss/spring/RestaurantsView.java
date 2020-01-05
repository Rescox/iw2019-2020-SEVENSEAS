package es.uca.iw.sss.spring;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import es.uca.iw.sss.spring.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static es.uca.iw.sss.spring.utils.SecurityUtils.getUser;

@Route(value = "Restaurant", layout = MainLayout.class)
@PageTitle("Restaurant")
public class RestaurantsView extends FormLayout {
    private Grid<Restaurant> restaurantGrid = new Grid<>(Restaurant.class);

    private RestaurantService restaurantService;
    public RestaurantsView(RestaurantService restaurantService) {

        //Datos
        this.restaurantService = restaurantService;
        User currentUser = getUser();
        Ship currentShip = currentUser.getShip();
        Set<Restaurant> restaurants = currentShip.getRestaurantSet();

        //Layouts
        VerticalLayout verticalLayout1 = new VerticalLayout();
        verticalLayout1.setHeight("50%");
        AppLayout appLayout = new AppLayout();
        appLayout.setPrimarySection(AppLayout.Section.DRAWER);
        DrawerToggle drawerToggle = new DrawerToggle();
        Icon icon = new Icon(VaadinIcon.ACCORDION_MENU);
        drawerToggle.setIcon(icon);

        //Mostrar todos los nombres de los restaurantes
        Tabs tabs = new Tabs();
        for(Restaurant r: restaurants)
        {
            tabs.add(createTab(r.getName(), RestaurantDetailsView.class));
            tabs.add(createTab(r.getName(), RestaurantDetailsView.class));
            tabs.add(createTab(r.getName(), RestaurantDetailsView.class));
            tabs.add(createTab(r.getName(), ServicesView.class));
            tabs.add(createTab(r.getName(), RestaurantDetailsView.class));
        }

        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        appLayout.addToDrawer(tabs);
        verticalLayout1.add(appLayout);
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(verticalLayout1);
        horizontalLayout.setWidth("50%");
        horizontalLayout.setHeight("50%");
        setHeight("50%");
        add(horizontalLayout);




        /*addToNavbar(new DrawerToggle(), img);
        Tabs tabs = new Tabs(new Tab("Home"), new Tab("About"));
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        addToDrawer(tabs);
        /*this.restaurantService = restaurantService;
        User currentUser = getUser();
        Ship currentShip = currentUser.getShip();
        Set<Restaurant> restaurants = currentShip.getRestaurantSet();
        VerticalLayout verticalLayout1 = new VerticalLayout();
        restaurantGrid.setColumns("id");
        restaurantGrid.setItems(restaurants);
        verticalLayout1.add(restaurantGrid);
        add(verticalLayout1);*/
    }

    public static Tab createTab(Component content) {
        final Tab tab = new Tab();
        tab.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
        tab.add(content);
        return tab;
    }

    public static Tab createTab( String title, Class<? extends Component> viewClass) {
        return createTab(populateLink(new RouterLink(null, viewClass), title));
    }

    public static <T extends HasComponents> T populateLink(T a,  String title) {
        a.add(title);
        return a;
    }

}

/*
@Route(value = "Restaurant", layout = MainLayout.class)
@PageTitle("Reservation")
public class RestaurantsView extends VerticalLayout {


    private RestaurantService restaurantService;
    private Grid<Restaurant> restaurantGrid = new Grid<>(Restaurant.class);

    @Autowired
    public RestaurantsView(RestaurantService restaurantService)
    {
        this.restaurantService = restaurantService;
        User currentUser = SecurityUtils.getUser();
        Ship currentShip = currentUser.getShip();
        Set<Restaurant> restaurants = currentShip.getRestaurantSet();
        VerticalLayout verticalLayout1 = new VerticalLayout();

    }

}
*/