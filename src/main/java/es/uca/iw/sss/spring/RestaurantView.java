package es.uca.iw.sss.spring;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.persistence.*;

@Route(value = "Restaurant", layout = MainLayout.class)
@PageTitle("Reservation")
public class RestaurantView extends VerticalLayout {

    public RestaurantView(){

        VerticalLayout restaurantLayout = new VerticalLayout();
        FormLayout formLayout = new FormLayout();
        HorizontalLayout horizontalLayout = new HorizontalLayout();

        Image img1=new Image("images/restaurants1.jpg", "images/restaurants1.jpg");
        img1.setWidth("100%");
        formLayout.add(img1);



    }
}
