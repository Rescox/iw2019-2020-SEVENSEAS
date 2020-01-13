package es.uca.iw.sss.spring;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static es.uca.iw.sss.spring.utils.SecurityUtils.getUser;

@Route(value = "ShopsView", layout = MainLayout.class)
@PageTitle("Shops")
public class ShopsView extends VerticalLayout {

    private ShopService shopService;
    private ShopRepository shopRepository;
    private ShipService shipService;
    private ShipRepository shipRepository;
    private UserService userService;


    public ShopsView(ShopService shopService) {

        //Datos
        User currentUser = getUser();
        Ship currentShip = currentUser.getShip();
        Set<Shop> shops = currentShip.getShopSet();

        //Layouts
        VerticalLayout verticalLayout1 = new VerticalLayout();
        verticalLayout1.setWidth("30%");

        //Mostrar todos los nombres de los restaurantes
        for(Shop s: shops)
        {
            Button boton = new Button(s.getName(), e -> ShopView(s.getId()));
            verticalLayout1.add(boton);
        }

        add(verticalLayout1);
    }

    public void ShopView(Long id_shop)
    {
        UI.getCurrent().navigate(ShopView.class, id_shop);
        UI.getCurrent().getPage().reload();
    }
}
