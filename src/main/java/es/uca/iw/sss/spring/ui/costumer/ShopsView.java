package es.uca.iw.sss.spring.ui.costumer;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.sss.spring.backend.entities.Ship;
import es.uca.iw.sss.spring.backend.entities.Shop;
import es.uca.iw.sss.spring.backend.entities.User;
import es.uca.iw.sss.spring.backend.repositories.ShopRepository;
import es.uca.iw.sss.spring.backend.services.ShopService;
import es.uca.iw.sss.spring.ui.common.MainLayout;
import es.uca.iw.sss.spring.utils.SecurityUtils;
import org.springframework.security.access.annotation.Secured;

import java.util.Set;

@Secured("customer")
@Route(value = "Shops", layout = MainLayout.class)
@PageTitle("Shops")
public class ShopsView extends VerticalLayout {

    private Grid<Shop> shopGrid = new Grid<>(Shop.class);
    private ShopService shopService;
    private final ShopRepository shopRepository;

    public ShopsView(ShopService shopService, ShopRepository shopRepository)
    {
        this.shopService = shopService;
        this.shopRepository = shopRepository;
        User currentUser = SecurityUtils.getUser();
        Ship currentShip = currentUser.getShip();
        Set<Shop> shops = currentShip.getShopSet();
        shopGrid.setHeightByRows(true);
        shopGrid.setItems(shops);
        shopGrid.setColumns("name","open_time","close_time");
        shopGrid.setWidth("50%");
        setAlignItems(Alignment.CENTER);

        shopGrid.setSelectionMode(Grid.SelectionMode.NONE);
        shopGrid.setItemDetailsRenderer(TemplateRenderer.<Shop>of(
                "<div style='border: 1px solid gray; padding: 10px; width: 100%; box-sizing: border-box;'>"
                        + "<div><b>[[item.description]]!</b></div>"
                        + "<div></div>"
                        + "</div>")
                .withProperty("description", Shop::getDescription)
                .withEventHandler("handleClick", shop -> {
                    shopGrid.getDataProvider().refreshItem(shop);
                }));

        add(shopGrid);
    }
}