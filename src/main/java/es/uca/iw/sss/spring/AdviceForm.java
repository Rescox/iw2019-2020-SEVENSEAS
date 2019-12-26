package es.uca.iw.sss.spring;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;


@Route("adviceform")
@PageTitle("Advice Registration")
public class AdviceForm  extends AppLayout {
    private TextField advices = new TextField("License Plate");
    private Dialog dialog = new Dialog();
    private AdviceRepository adviceRepository;
    private AdviceService adviceService;
    private Advice advice = new Advice();
    private BeanValidationBinder<Advice> binder = new BeanValidationBinder<>(Advice.class);

    public AdviceForm(AdviceService adviceService) {

        FormLayout formLayout = new FormLayout();
        this.adviceService = adviceService;
        VerticalLayout verticalLayout = new VerticalLayout();
        advices.setRequiredIndicatorVisible(true);
        binder.bindInstanceFields(this);
        Button register = new Button("Register",  event -> {
            dialog.close();
            registerAdvice();

        });
        Button cancel = new Button("Cancel",  event -> {
            dialog.close();
            UI.getCurrent().navigate(ManageShipView.class);
        });
        dialog.add(register, cancel);
        register.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        register.addClickShortcut(Key.ENTER);
        cancel.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        HorizontalLayout buttons = new HorizontalLayout(register, cancel);
        verticalLayout.add(advices,buttons);
        formLayout.add(verticalLayout);
        setContent(formLayout);
    }

    private void registerAdvice() {
        advice.setAdvice(advices.getValue());
        adviceService.create(advice);
        UI.getCurrent().navigate(WelcomeView.class);
        UI.getCurrent().getPage().reload();
    }

    private static Tab createTab(Component content) {
        final Tab tab = new Tab();
        tab.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
        tab.add(content);
        return tab;
    }

    private static Tab createTab(VaadinIcon icon, String title, Class<? extends Component> viewClass) {
        return createTab(populateLink(new RouterLink(null, viewClass), icon, title));
    }

    private static <T extends HasComponents> T populateLink(T a, VaadinIcon icon, String title) {
        a.add(icon.create());
        a.add(title);
        return a;
    }

}
