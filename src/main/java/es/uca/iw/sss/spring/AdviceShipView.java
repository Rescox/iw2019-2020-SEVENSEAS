package es.uca.iw.sss.spring;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.sss.spring.utils.SecurityUtils;

import java.util.Set;

@Route(value = "AdviceShip", layout = MainLayout.class)
@PageTitle("AdviceShip")
public class AdviceShipView extends FormLayout {
    private Grid<AdviceShip> adviceGrid = new Grid<>(AdviceShip.class);
    private AdviceShipService adviceService;
    public AdviceShipView(AdviceShipService adviceService) {
        this.adviceService = adviceService;
        User currentUser = SecurityUtils.getUser();
        Ship currentShip = currentUser.getShip();
        Set<AdviceShip> advices = currentShip.getAdviceSet();
        VerticalLayout verticalLayout1 = new VerticalLayout();
        adviceGrid.setColumns("advice");
        adviceGrid.setItems(advices);
        verticalLayout1.add(adviceGrid);
        add(verticalLayout1);
    }

}






