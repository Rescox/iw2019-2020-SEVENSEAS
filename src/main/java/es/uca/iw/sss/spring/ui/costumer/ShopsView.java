package es.uca.iw.sss.spring;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import es.uca.iw.sss.spring.MainLayout;
import es.uca.iw.sss.spring.Shop;
import es.uca.iw.sss.spring.utils.SecurityUtils;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;

import com.vaadin.flow.component.button.Button;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

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