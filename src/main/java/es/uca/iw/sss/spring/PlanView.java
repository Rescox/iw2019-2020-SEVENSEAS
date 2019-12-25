package es.uca.iw.sss.spring;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.sss.spring.utils.SecurityUtils;


@Route(value = "Plan", layout = MainLayout.class)
@PageTitle("Plan")
public class PlanView extends VerticalLayout {

    public PlanView() {
        User currentUser = SecurityUtils.getUser();
        Ship currentShip = currentUser.getShip();
        H2 h2plano = new H2("Plano");
        H2 h2leyenda = new H2("Leyenda");
        Image shipplane = new Image(currentShip.getPlane(), "asdasd");
        Image shippleyend = new Image(currentShip.getLegend(), "asdasd");
        VerticalLayout verticalLayout1 = new VerticalLayout();
        verticalLayout1.add(h2plano, shipplane, h2leyenda, shippleyend);
        FormLayout formLayout = new FormLayout();
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 3, FormLayout.ResponsiveStep.LabelsPosition.ASIDE));
        formLayout.add(verticalLayout1);
        setHorizontalComponentAlignment(Alignment.CENTER,formLayout);

        add(formLayout);
    }

}







