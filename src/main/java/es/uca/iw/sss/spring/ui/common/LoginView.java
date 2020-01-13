package es.uca.iw.sss.spring.ui.common;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.login.AbstractLogin;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import es.uca.iw.sss.spring.ui.admin.ManageShipView;
import es.uca.iw.sss.spring.ui.manager.ManagerWelcome;
import es.uca.iw.sss.spring.utils.CustomRequestCache;
import es.uca.iw.sss.spring.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

@HtmlImport("frontend://bower_components/iron-form/iron-form.html") //
/**
 * A Designer generated component for the log-in template.
 *
 * <p>Designer will add and remove fields with @Id mappings but does not overwrite or otherwise
 * change this file.
 */
@Route(value = LoginView.ROUTE)
@Tag("sa-login-view")
@PageTitle("Iniciar Sesión")
@JsModule("./styles/shared-styles.js")
public class LoginView extends VerticalLayout {

  public static final String ROUTE = "login";
  private LoginOverlay login;
  private CustomRequestCache requestCache;
  private AuthenticationManager auth;

  @Autowired
  public LoginView(AuthenticationManager auth, CustomRequestCache requestCache) {
    this.auth = auth;
    this.requestCache = requestCache;
    login = new LoginOverlay();
    login.setOpened(true);
    LoginI18n I18n = LoginI18n.createDefault();
    I18n.setHeader(new LoginI18n.Header());
    I18n.getHeader().setTitle("Seven Seas");
    I18n.getHeader().setDescription("Introduce your user and password");
    I18n.setForm(new LoginI18n.Form());
    I18n.getForm().setSubmit("Sign in");
    I18n.getForm().setTitle("Sign in");
    I18n.getForm().setUsername("User");
    I18n.getForm().setPassword("Password");
    login.setI18n(I18n);
    login.setForgotPasswordButtonVisible(true);
    login.addLoginListener(e -> loginAction(e));
    add(login);
  }

    private void loginAction(AbstractLogin.LoginEvent e) {
        if(SecurityUtils.isUserLoggedIn()){
            UI.getCurrent().navigate(WelcomeView.class);
            UI.getCurrent().getPage().reload();
        }else
        if(authenticate(e.getUsername(), e.getPassword())){
            login.close();
            if(SecurityUtils.hasRole("customer"))
                UI.getCurrent().navigate(WelcomeView.class);
            if(SecurityUtils.hasRole("admin"))
                UI.getCurrent().navigate(ManageShipView.class);
            if(SecurityUtils.hasRole("manager"))
                UI.getCurrent().navigate(ManagerWelcome.class);
            UI.getCurrent().getPage().reload();
        }else{
            login.setError(true);
        }
    }

  public boolean authenticate(String user, String password) {
    try {
     System.out.println(user);
     System.out.println(password);

        Authentication token =
          auth.authenticate(new UsernamePasswordAuthenticationToken(user, password));
      SecurityContextHolder.getContext().setAuthentication(token);
      return true;
    } catch (AuthenticationException e) {

        e.printStackTrace();
      return false;
    }
  }
}
