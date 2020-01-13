package es.uca.iw.sss.spring;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.Set;

import static es.uca.iw.sss.spring.utils.SecurityUtils.getUser;

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
        this.photourl =  shop.getPhoto();
    }
}
