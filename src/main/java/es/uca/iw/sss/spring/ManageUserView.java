package es.uca.iw.sss.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;


@Route(value = "ManageUser", layout = MainLayout.class)
@PageTitle("Manage User")
public class ManageUserView extends VerticalLayout {
    final TextField filter;
    final Grid<User> userGrid;
    private final UserRepository userRepository;
    private final RegisterForm registerForm;
    private final Button addUser;
    private ShipService shipService;

    public ManageUserView(UserRepository userRepository, RegisterForm registerForm)
    {
        this.userRepository = userRepository;
        this.registerForm = registerForm;
        this.userGrid = new Grid<>(User.class);
        this.filter = new TextField();
        this.addUser = new Button("New User", VaadinIcon.PLUS.create());

        HorizontalLayout actions = new HorizontalLayout(filter, addUser);
        add(actions, userGrid, registerForm);

        userGrid.setColumns("id","firstName","lastName","dni","email","username","role");
        filter.setPlaceholder("Filter by username");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listUsers(e.getValue()));

        userGrid.asSingleSelect().addValueChangeListener(e -> {
            registerForm.editUser(e.getValue());
        });
        addUser.addClickListener(e -> registerForm.editUser(new User()));
        registerForm.setChangeHandler(() -> {
            registerForm.setVisible(false);
            listUsers(filter.getValue());
        });
        listUsers(null);
    }

    void listUsers(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            userGrid.setItems(userRepository.findAll());
        }
        else {
            userGrid.setItems(userRepository.findByUsernameStartsWithIgnoreCase(filterText));
        }
    }
}