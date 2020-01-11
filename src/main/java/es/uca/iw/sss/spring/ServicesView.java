package es.uca.iw.sss.spring;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "ServicesView", layout = MainLayout.class)
@PageTitle("Services")
public class ServicesView extends VerticalLayout {

    public ServicesView() {

        VerticalLayout servicesLayout = new VerticalLayout();
        FormLayout formLayout = new FormLayout();
        HorizontalLayout buttonsLayout = new HorizontalLayout();

        Image img1=new Image("images/restaurants1.jpg", "images/restaurants1.jpg");
        img1.setWidth("300px");
        img1.addClickListener(e -> UI.getCurrent().navigate(RestaurantView.class));

        Image img2=new Image("images/fashion1.jpg","images/fashion1.jpg");
        img2.setWidth("300px");
        img2.addClickListener(e -> UI.getCurrent().navigate(ShopsView.class));

        Image img3=new Image("/images/wellness1.jpg","images/wellness1.jpg");
        img3.setWidth("300px");

        formLayout.add(img1, img2, img3);
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 3, FormLayout.ResponsiveStep.LabelsPosition.ASIDE));
        buttonsLayout.add(formLayout);
        servicesLayout.add(buttonsLayout);
        servicesLayout.setHorizontalComponentAlignment(Alignment.CENTER,buttonsLayout);
        add(servicesLayout);

    }

    }






