package es.uca.iw.sss.spring.ui.admin;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import es.uca.iw.sss.spring.backend.entities.AdviceCity;
import es.uca.iw.sss.spring.backend.repositories.AdviceCityRepository;
import es.uca.iw.sss.spring.backend.services.AdviceCityService;
import es.uca.iw.sss.spring.backend.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class AdviceCityForm extends VerticalLayout implements KeyNotifier {
  private final AdviceCityRepository adviceRepository;
  private AdviceCity advices;
  private TextField advice = new TextField("Advice");
  private TextField cityId = new TextField("City id");
  private AdviceCityService adviceService;
  private CityService cityService;
    private Button save = new Button("Save", VaadinIcon.CHECK.create());
    private Button cancel = new Button("Reset");
    private Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    private HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);
  private ChangeHandler changeHandler;

  @Autowired
  public AdviceCityForm(
      AdviceCityRepository adviceRepository,
      AdviceCityService adviceService,
      CityService cityService) {
    this.adviceRepository = adviceRepository;
    this.adviceService = adviceService;
    this.cityService = cityService;
    add(advice, cityId, actions);

    setSpacing(true);

    save.getElement().getThemeList().add("primary");
    delete.getElement().getThemeList().add("error");

    addKeyPressListener(Key.ENTER, e -> save());

    save.addClickListener(e -> save());
    delete.addClickListener(e -> delete());
    cancel.addClickListener(e -> editAdvice(advices));
    setVisible(false);
  }

    private void delete() {
    adviceRepository.delete(advices);
    changeHandler.onChange();
    UI.getCurrent().getPage().reload();
  }

    private void save() {
    advices.setAdvice(advice.getValue());
    advices.setCity(cityService.findById(Long.parseLong(cityId.getValue())).get());
    adviceService.create(advices);
    changeHandler.onChange();
    UI.getCurrent().getPage().reload();
  }

  public interface ChangeHandler {
    void onChange();
  }

  public final void editAdvice(AdviceCity adviceEdit) {
    if (adviceEdit == null) {
      setVisible(false);
      return;
    }
    final boolean persisted = adviceEdit.getId() != null;
    if (persisted) {
      advices = adviceRepository.findById(adviceEdit.getId()).get();
    } else {
      advices = adviceEdit;
    }
    cancel.setVisible(persisted);
    if (advices.getAdvice() != null) {
      advice.setValue(advices.getAdvice());
    } else {
      advice.setValue("");
    }
    if (advices.getCity() != null) {
      this.cityId.setEnabled(false);
      cityId.setValue(Long.toString(adviceEdit.getCity().getId()));
    } else {
      cityId.setValue("");
    }

    setVisible(true);

    advice.focus();
  }

  public void setChangeHandler(ChangeHandler h) {
    changeHandler = h;
  }
}
