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

@Route(value = "Tour", layout = MainLayout.class)
@PageTitle("Tours")
public class TourView extends HorizontalLayout {

    public TourView() {
        Image img1=new Image("/frontend/images/beach-photos.jpg","/frontend/images/beach-photos.jpg");
        img1.setWidth("200px");
        Image img2=new Image("/frontend/images/water-background-.jpg","/frontend/images/water-background-.jpg");
        img2.setWidth("200px");
        Image img3=new Image("/frontend/images/water-background-.jpg","/frontend/images/water-background-.jpg");
        img3.setWidth("200px");
        add(img1,img2,img3);

    }

}






