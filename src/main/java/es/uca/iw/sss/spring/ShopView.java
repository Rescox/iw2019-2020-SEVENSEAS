package es.uca.iw.sss.spring;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
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

    @Autowired
    public ShopView(ShopRepository shopRepository, ShopService shopService) {

        this.shopService = shopService;
        this.shopRepository = shopRepository;

    }

    @PostConstruct
    public void init(){
        H1 name = new H1(""+shop.getId()+"");
        //name.add(shop.getName());
        add(name);
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setShop(Shop shop) { this.shop = shop; }

    //En esta funcion intento asignar el valor pasado por la url a el parametro id de ShopView pero jamás se asigna
    public void setParameter(BeforeEvent beforeEvent, Long id) {
        Location location = beforeEvent.getLocation();
        System.out.println(location.getSegments().get(1));
        if(shopRepository.findById(id).isPresent()){
            //setId(Long.parseLong(location.getSegments().get(1)));
            setShop(shopRepository.findById(id).get());
        }
    }
}
