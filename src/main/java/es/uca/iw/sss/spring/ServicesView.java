package es.uca.iw.sss.spring;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
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

@Route(value = "Services", layout = MainLayout.class)
@PageTitle("Services")
public class ServicesView extends HorizontalLayout {

    public ServicesView() {

        VerticalLayout servicesLayout = new VerticalLayout();
        HorizontalLayout buttonsLayout = new HorizontalLayout();

        Image img1=new Image("images/restaurantes .jpeg", "images/restaurantes .jpeg");
        img1.setWidth("300px");
        img1.addClickListener(e -> System.out.println("Click"));

        Image img2=new Image("images/shopping.jpg","images/shopping.jpg");
        img2.setWidth("300px");

        Image img3=new Image("/images/spa.jpg","images/spa.jpg");
        img3.setWidth("300px");


        buttonsLayout.add(img1,img2,img3);
        servicesLayout.add(buttonsLayout);
        servicesLayout.setHorizontalComponentAlignment(Alignment.CENTER,buttonsLayout);
        add(servicesLayout);

        }

    }






