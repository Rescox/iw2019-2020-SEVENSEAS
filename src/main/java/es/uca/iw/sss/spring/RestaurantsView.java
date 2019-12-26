package es.uca.iw.sss.spring;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value ="RestaurantsView", layout = MainLayout.class)
@PageTitle("Restaurants")
public class RestaurantsView extends VerticalLayout {

    public RestaurantsView()
    {
        VerticalLayout servicesLayout = new VerticalLayout();
        FormLayout formLayout = new FormLayout();
        HorizontalLayout buttonsLayout = new HorizontalLayout();

        Image img1=new Image("images/restaurants1.jpg", "images/restaurants1.jpg");
        img1.setWidth("300px");
        img1.addClickListener(e -> UI.getCurrent().navigate(RestaurantsView.class));

        Image img2=new Image("images/fashion1.jpg","images/fashion1.jpg");
        img2.setWidth("300px");

        Image img3=new Image("/images/wellness1.jpg","images/wellness1.jpg");
        img3.setWidth("300px");

        formLayout.add(img1);
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 3, FormLayout.ResponsiveStep.LabelsPosition.ASIDE));
        buttonsLayout.add(formLayout);
        servicesLayout.add(buttonsLayout);
        servicesLayout.setHorizontalComponentAlignment(Alignment.CENTER,buttonsLayout);
        add(servicesLayout);
    }
}
