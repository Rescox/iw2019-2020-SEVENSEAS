package es.uca.iw.sss.spring;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "ManageShip", layout = MainLayout.class)
@PageTitle("Manage Ship")
public class ManageShipView extends VerticalLayout {

    public ManageShipView()
    {
        Button register = new Button("Register Ship", event -> ShipForm());
        add(register);
    }

    public void ShipForm() {
        UI.getCurrent().navigate(ShipForm.class);
        UI.getCurrent().getPage().reload();
    }
}
