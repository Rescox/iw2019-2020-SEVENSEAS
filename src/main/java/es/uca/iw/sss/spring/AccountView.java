package es.uca.iw.sss.spring;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
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
public class AccountView extends FormLayout {
    private Grid<User> grid = new Grid<>(User.class);
    private AccountService accountService;
    public AccountView(UserService userService) {
        this.accountService = accountService;
        H2 DatosPersonales = new H2("DatosPersonales");
        grid.setColumns("firstName", "lastName", "dni", "email", "user");

        VerticalLayout content = new VerticalLayout();
        HorizontalLayout HL1 = new HorizontalLayout();
        HL1.add(DatosPersonales);
        content.add(HL1,grid);
        add(content);
    }

}






