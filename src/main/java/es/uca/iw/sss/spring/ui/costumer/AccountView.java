package es.uca.iw.sss.spring.ui.costumer;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.sss.spring.backend.entities.Account;
import es.uca.iw.sss.spring.backend.entities.User;
import es.uca.iw.sss.spring.backend.services.AccountService;
import es.uca.iw.sss.spring.backend.services.UserService;
import es.uca.iw.sss.spring.ui.common.MainLayout;
import org.springframework.security.access.annotation.Secured;

import static es.uca.iw.sss.spring.utils.SecurityUtils.getUser;

@Secured("customer")
@Route(value = "Account", layout = MainLayout.class)
@PageTitle("Account")
public class AccountView extends FormLayout {
    private Grid<User> userGrid = new Grid<>(User.class);
    private Grid<Account> accountGrid = new Grid<>(Account.class);

    private AccountService accountService;
    public AccountView(UserService userService) {
        this.accountService = accountService;
        H2 DatosPersonales = new H2("Datos Personales");
        H2 DatosCuenta = new H2("Datos Cuenta");
        userGrid.setColumns("firstName", "lastName", "dni", "email", "user");
        accountGrid.setColumns("serviceName", "date", "price");
        userGrid.setMaxHeight("100px");
        userGrid.setItems(getUser());

        VerticalLayout content = new VerticalLayout();
        HorizontalLayout HL1 = new HorizontalLayout();
        HL1.add(DatosPersonales);
        content.add(HL1, userGrid, DatosCuenta, accountGrid);
        add(content);
    }

}






