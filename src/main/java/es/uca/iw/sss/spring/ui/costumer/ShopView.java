package es.uca.iw.sss.spring.ui.costumer;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import es.uca.iw.sss.spring.backend.entities.Shop;
import es.uca.iw.sss.spring.backend.repositories.ShopRepository;
import es.uca.iw.sss.spring.backend.services.ShopService;
import es.uca.iw.sss.spring.ui.common.MainLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

@Secured("customer")
@Route(value = "ShopView", layout = MainLayout.class)
@PageTitle("Shop")
public class ShopView extends VerticalLayout implements HasUrlParameter<Long> {

    private ShopService shopService;
    private ShopRepository shopRepository;
    private Long id;
    private Shop shop;
    private H1 name;
    private H2 description;
    private String photourl;

    @Autowired
    public ShopView(ShopRepository shopRepository, ShopService shopService) {

        this.shopService = shopService;
        this.shopRepository = shopRepository;
        this.name = new H1();
        this.description = new H2();
        Image img = new Image("images/zara.jpg","images/zara.jpg");
        img.setWidthFull();
        img.setHeight("20%");
        add(img,name,description);

    }


    //En esta funcion intento asignar el valor pasado por la url a el parametro id de ShopView pero jamás se asigna
    public void setParameter(BeforeEvent beforeEvent, Long id) {
        Location location = beforeEvent.getLocation();
        System.out.println(location.getSegments().get(1));
        id = Long.parseLong(location.getSegments().get(1));
        this.shop = shopService.findById(id).get();
        this.name.setText(shop.getName());
        this.description.setText(shop.getDescription());
    }
}
