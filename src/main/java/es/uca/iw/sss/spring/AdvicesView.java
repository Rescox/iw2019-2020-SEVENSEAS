package es.uca.iw.sss.spring;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.sss.spring.utils.SecurityUtils;

import java.util.List;
import java.util.Set;

import static es.uca.iw.sss.spring.utils.SecurityUtils.getUser;

@Route(value = "Advice", layout = MainLayout.class)
@PageTitle("Advice")
public class AdvicesView extends FormLayout {
    private Grid<Advice> adviceGrid = new Grid<>(Advice.class);
    private AdviceService adviceService;

    public AdvicesView(AdviceService adviceService) {
        this.adviceService = adviceService;
        User currentUser = SecurityUtils.getUser();
        Ship currentShip = currentUser.getShip();
        Set<Advice> advices = currentShip.getAdviceSet();
        VerticalLayout verticalLayout1 = new VerticalLayout();
        adviceGrid.setColumns("advice");
        adviceGrid.setItems(advices);
        verticalLayout1.add(adviceGrid);
        add(verticalLayout1);
    }

}






