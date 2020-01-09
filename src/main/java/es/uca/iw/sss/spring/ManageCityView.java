package es.uca.iw.sss.spring;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "ManageCity", layout = MainLayout.class)
@PageTitle("Manage Restaurant")
public class ManageCityView extends VerticalLayout {

    public  ManageCityView()
    {

    }
}
