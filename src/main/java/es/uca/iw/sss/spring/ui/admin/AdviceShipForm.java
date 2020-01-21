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
import es.uca.iw.sss.spring.backend.entities.AdviceShip;
import es.uca.iw.sss.spring.backend.repositories.AdviceShipRepository;
import es.uca.iw.sss.spring.backend.services.AdviceShipService;
import es.uca.iw.sss.spring.backend.services.ShipService;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class AdviceShipForm extends VerticalLayout implements KeyNotifier {
  private final AdviceShipRepository adviceRepository;
  private AdviceShip advices;
  private TextField advice = new TextField("Advice");
  private TextField shipLicensePlate = new TextField("Ship License Plate");
  private AdviceShipService adviceService;
  private ShipService shipService;
  private Button save = new Button("Save", VaadinIcon.CHECK.create());
  private Button cancel = new Button("Reset");
  private Button delete = new Button("Delete", VaadinIcon.TRASH.create());
  private HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);
  private ChangeHandler changeHandler;

  @Autowired
  public AdviceShipForm(
      AdviceShipRepository adviceRepository,
      AdviceShipService adviceService,
      ShipService shipService) {
    this.adviceRepository = adviceRepository;
    this.adviceService = adviceService;
    this.shipService = shipService;
    add(advice, shipLicensePlate, actions);

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
    advices.setShip(shipService.findByLicensePlate(shipLicensePlate.getValue()));
    adviceService.create(advices);
    changeHandler.onChange();
    UI.getCurrent().getPage().reload();
  }

  public interface ChangeHandler {
    void onChange();
  }

  public final void editAdvice(AdviceShip adviceEdit) {
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
    if (advices.getShip() != null) {
      this.shipLicensePlate.setEnabled(false);
      shipLicensePlate.setValue(advices.getShip().getLicensePlate());
    } else {
      shipLicensePlate.setValue("");
    }

    setVisible(true);

    advice.focus();
  }

  public void setChangeHandler(ChangeHandler h) {
    changeHandler = h;
  }
}
