package es.uca.iw.sss.spring;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import static es.uca.iw.sss.spring.utils.SecurityUtils.getUser;

@Route(value = "Advice", layout = MainLayout.class)
@PageTitle("Advice")
public class AdvicesView extends FormLayout {
    private Grid<Advice> adviceGrid = new Grid<>(Advice.class);
    private AdviceService adviceService;
    public AdvicesView(AdviceService adviceService) {
        this.adviceService = adviceService;
    }

}






