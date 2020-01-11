package es.uca.iw.sss.spring;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
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
  Button save = new Button("Save", VaadinIcon.CHECK.create());
  Button cancel = new Button("Cancel");
  Button delete = new Button("Delete", VaadinIcon.TRASH.create());
  HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);
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

  void delete() {
    adviceRepository.delete(advices);
    changeHandler.onChange();
  }

  void save() {
    advices.setAdvice(advice.getValue());
    advices.setCity(cityService.findById(Long.parseLong(cityId.getValue())).get());
    adviceService.create(advices);
    changeHandler.onChange();
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
    advice.setValue(advices.getAdvice());
    if (advices.getCity() != null) {
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
