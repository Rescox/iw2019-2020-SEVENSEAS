package es.uca.iw.sss.spring.ui.admin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.*;
import es.uca.iw.sss.spring.backend.entities.Shop;
import es.uca.iw.sss.spring.backend.repositories.ShopRepository;
import es.uca.iw.sss.spring.backend.services.ShipService;
import es.uca.iw.sss.spring.ui.common.MainLayout;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.annotation.Secured;

import java.util.Set;

@Secured("admin")
@Route(value = "ManageShop", layout = MainLayout.class)
@PageTitle("Manage Shop")
public class ManageShopView extends VerticalLayout implements HasUrlParameter<String> {
  final TextField filter;
  final Grid<Shop> shopGrid;
  private final ShopRepository shopRepository;
  private final ShopForm shopForm;
  private final Button addShop;
  private ShipService shipService;
  private Set<Shop> shops;

  public ManageShopView(ShopRepository shopRepository, ShopForm shopForm, ShipService shipService) {
    this.shopRepository = shopRepository;
    this.shipService = shipService;
    this.shopForm = shopForm;
    this.shopGrid = new Grid<>(Shop.class);
    this.filter = new TextField();
    this.addShop = new Button("New Shop", VaadinIcon.PLUS.create());

    HorizontalLayout actions = new HorizontalLayout(filter, addShop);
    add(actions, shopGrid, shopForm);

    shopGrid.setColumns("id", "name", "description", "open_time","close_time", "ship.licensePlate");
    shopGrid.getColumnByKey("ship.licensePlate").setHeader("Ship License Plate");
      filter.setPlaceholder("Filter by name");
      filter.setValueChangeMode(ValueChangeMode.EAGER);
      filter.addValueChangeListener(e -> listShop(e.getValue()));

      shopGrid
              .asSingleSelect()
              .addValueChangeListener(
                      e -> {
                          shopForm.editShop(e.getValue());
                      });
      addShop.addClickListener(e -> {
          shopForm.getShipPlate().setEnabled(true);
          shopForm.editShop(new Shop());
      });
      shopForm.setChangeHandler(
              () -> {
                  shopForm.setVisible(false);
                  listShop(filter.getValue());
              });
      listShop(null);
  }

  void listShop(String filterText) {
    if (StringUtils.isEmpty(filterText)) {
      shopGrid.setItems(shopRepository.findAll());
    } else {
      shopGrid.setItems(shopRepository.findByNameStartsWithIgnoreCase(filterText));
    }
  }

  @Override
  public void setParameter(BeforeEvent beforeEvent, String licensePlate) {
    Location location = beforeEvent.getLocation();
    licensePlate = location.getSegments().get(1);
    shops = shipService.findByLicensePlate(licensePlate).getShopSet();
    shopGrid.setItems(shops);
  }
}
