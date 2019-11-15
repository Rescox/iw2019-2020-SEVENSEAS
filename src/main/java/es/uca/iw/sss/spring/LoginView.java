package es.uca.iw.sss.spring;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.login.AbstractLogin;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.i18n.I18NProvider;
import com.vaadin.flow.router.*;
import com.vaadin.flow.templatemodel.TemplateModel;
import es.uca.iw.sss.spring.utils.SecurityUtils;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@HtmlImport("frontend://bower_components/iron-form/iron-form.html") //
/**
 * A Designer generated component for the log-in template.
 *
 * <p>Designer will add and remove fields with @Id mappings but does not overwrite or otherwise
 * change this file.
 */
@Route
@Tag("login")
@PageTitle("Iniciar Sesión")
@JsModule("./styles/shared-styles.js")
public class LoginView extends VerticalLayout {

  public static final String ROUTE = "login";
  private LoginOverlay login;
  private AuthenticationManager auth;

  /** Creates a new LogIn. */
  public LoginView(AuthenticationManager auth) {
    this.auth = auth;
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
          UI.getCurrent().navigate(MainView.class);
          UI.getCurrent().getPage().reload();
      }else
          if(authenticate(e.getUsername(), e.getPassword())){
              login.close();
              UI.getCurrent().navigate(MainView.class);
              UI.getCurrent().getPage().reload();
          }else{
              login.setError(true);
          }
  }

  public boolean authenticate(String user, String password) {
    try {
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
