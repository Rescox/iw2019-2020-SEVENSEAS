package es.uca.iw.sss.spring;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import java.util.Set;

import static es.uca.iw.sss.spring.utils.SecurityUtils.getUser;

@Route(value = "RestaurantView", layout = MainLayout.class)
@PageTitle("Restaurant")
public class RestaurantView extends AppLayout {

    private Grid<Restaurant> restaurantGrid = new Grid<>(Restaurant.class);
    private ReservationService reservationService;
    private UserService userService;


    private RestaurantService restaurantService;
    public RestaurantView(RestaurantService restaurantService) {

        //Datos
        this.restaurantService = restaurantService;
        User currentUser = getUser();
        Ship currentShip = currentUser.getShip();
        Set<Restaurant> restaurants = currentShip.getRestaurantSet();

        //Layouts
        VerticalLayout verticalLayout1 = new VerticalLayout();
        verticalLayout1.setWidth("30%");

        //Mostrar todos los nombres de los restaurantes
        Tabs tabs = new Tabs();
        for(Restaurant r: restaurants)
        {
            Tab tab = new Tab();
            tab.setLabel(r.getName());
            tabs.add(createTab(r.getName(), ReservationForm.class));
            tabs.add(createTab(r.getName(), ReservationForm.class));
            tabs.add(createTab(r.getName(), ReservationForm.class));
            tabs.add(createTab(r.getName(), ReservationForm.class));

        }

        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        verticalLayout1.add(tabs);
        setContent(verticalLayout1);
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

    public static Tab createTab2( String title, Class<? extends Component> viewClass) {
        return createTab(populateLink(new RouterLink(null, viewClass), title));
    }

    public static <T extends HasComponents> T populateLink(T a, String title) {
        a.add(title);
        return a;
    }

}
