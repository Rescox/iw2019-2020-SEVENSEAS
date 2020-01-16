package es.uca.iw.sss.spring.ui.costumer;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.sss.spring.ui.common.MainLayout;
import org.springframework.security.access.annotation.Secured;

@Secured("customer")
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
        img3.addClickListener(e -> UI.getCurrent().navigate(SpasView.class));

        Image img4=new Image("/images/events1.jpg","images/events1.jpg");
        img4.setWidth("300px");
        img4.addClickListener(e -> UI.getCurrent().navigate(EventsView.class));

        formLayout.add(img1, img2, img3, img4);
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 4, FormLayout.ResponsiveStep.LabelsPosition.ASIDE));
        buttonsLayout.add(formLayout);
        servicesLayout.add(buttonsLayout);
        servicesLayout.setHorizontalComponentAlignment(Alignment.CENTER,buttonsLayout);
        add(servicesLayout);

    }

    }






