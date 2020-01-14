package es.uca.iw.sss.spring.ui.costumer;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.sss.spring.backend.entities.Ship;
import es.uca.iw.sss.spring.backend.entities.Spa;
import es.uca.iw.sss.spring.backend.entities.User;
import es.uca.iw.sss.spring.backend.repositories.ShipRepository;
import es.uca.iw.sss.spring.backend.repositories.SpaRepository;
import es.uca.iw.sss.spring.backend.services.ShipService;
import es.uca.iw.sss.spring.backend.services.SpaService;
import es.uca.iw.sss.spring.backend.services.UserService;
import es.uca.iw.sss.spring.ui.common.MainLayout;

import java.util.Set;

import static es.uca.iw.sss.spring.utils.SecurityUtils.getUser;

@Route(value = "SpasView", layout = MainLayout.class)
@PageTitle("Spas")
public class SpasView extends VerticalLayout {

    private SpaService shopService;
    private SpaRepository shopRepository;
    private ShipService shipService;
    private ShipRepository shipRepository;
    private UserService userService;


    public SpasView(SpaService spaService) {

        //Datos
        User currentUser = getUser();
        Ship currentShip = currentUser.getShip();
        Set<Spa> spas = currentShip.getSpaSet();

        //Layouts
        VerticalLayout verticalLayout1 = new VerticalLayout();
        verticalLayout1.setWidth("30%");

        //Mostrar todos los nombres de los spas
        for(Spa s: spas)
        {
            Button boton = new Button(s.getName(), e -> SpasView(s.getId()));
            verticalLayout1.add(boton);
        }

        add(verticalLayout1);
    }

    public void SpasView(Long id_spa)
    {
        UI.getCurrent().navigate(SpaView.class, id_spa);
        UI.getCurrent().getPage().reload();
    }
}