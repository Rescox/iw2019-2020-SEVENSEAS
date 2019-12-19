package es.uca.iw.sss.spring;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.sss.spring.utils.SecurityUtils;

import static es.uca.iw.sss.spring.utils.SecurityUtils.getUser;

@Route(value = "Plan", layout = MainLayout.class)
@PageTitle("Plan")
public class PlanView extends FormLayout {

    public PlanView() {
        User currentUser = SecurityUtils.getUser();
        Ship currentShip = currentUser.getShip();
        Image shipplane = new Image(currentShip.getPlane(), "asdasd");

        add(shipplane);
    }

}







