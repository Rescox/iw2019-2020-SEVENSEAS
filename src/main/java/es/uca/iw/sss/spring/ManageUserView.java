package es.uca.iw.sss.spring;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value = "ManageUser", layout = MainLayout.class)
@PageTitle("Manage User")
public class ManageUserView extends VerticalLayout {
    Grid<User> userGrid = new Grid<>(User.class);
    UserService userService;
    public ManageUserView(UserService userService)
    {
        this.userService = userService;
        userGrid.setColumns("firstName", "lastName", "dni", "email", "username");
        userGrid.setItems(userService.findAll());
        Button register = new Button("Register User", event -> RegisterForm());
        add(userGrid,register);
    }

    public void RegisterForm() {
        UI.getCurrent().navigate(RegisterForm.class);
        UI.getCurrent().getPage().reload();
    }
}