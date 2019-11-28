package es.uca.iw.sss.spring;


import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "Welcome", layout = MainLayout.class)
@PageTitle("Welcome")
public class WelcomeView extends HorizontalLayout {

    public WelcomeView()
    {
        Image img1=new Image("/frontend/images/beach-photos.jpg","/frontend/images/beach-photos.jpg");
        img1.setWidth("200px");
        Image img2=new Image("/frontend/images/water-background-.jpg","/frontend/images/water-background-.jpg");
        img2.setWidth("200px");
        Image img3=new Image("/frontend/images/water-background-.jpg","/frontend/images/water-background-.jpg");
        img3.setWidth("200px");
        add(img1,img2,img3);
    }

}
