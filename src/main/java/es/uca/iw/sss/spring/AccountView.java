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

@Route(value = "Account", layout = MainLayout.class)
@PageTitle("Account")
public class AccountView extends HorizontalLayout {
    private Grid<User> grid = new Grid<>(User.class);
    private AccountService accountService;
    public AccountView(UserService userService) {
        this.accountService = accountService;
        H1 DatosPersonales = new H1("DatosPersonales");
        H1 Gastos = new H1("Gastos");
        VerticalLayout content = new VerticalLayout();
        HorizontalLayout HL1 = new HorizontalLayout();
        HL1.add(DatosPersonales, Gastos);
        content.add(HL1);
    }

}






