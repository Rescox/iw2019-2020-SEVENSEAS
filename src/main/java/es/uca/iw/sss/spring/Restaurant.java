package es.uca.iw.sss.spring;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Restaurant implements Serializable, Cloneable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private Long aforum;

  @Column(nullable = false)
  private String photo;

  @Column(nullable = false)
  private String phone;

  @ManyToOne private Ship ship;

  @OneToMany(fetch = FetchType.EAGER,mappedBy = "restaurant", cascade = CascadeType.REMOVE)
  private Set<Dish> dishSet = new HashSet<>();

  public Restaurant() {}

  public Restaurant(String name, Long aforum, String photo, String description, String phone) {
    this.name = name;
    this.aforum = aforum;
    this.photo = photo;
    this.description = description;
    this.phone = phone;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getAforum() {
    return aforum;
  }

  public void setAforum(Long aforum) {
    this.aforum = aforum;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Ship getShip() {
    return ship;
  }

  public void setShip(Ship ship) {
    this.ship = ship;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Set<Dish> getDishSet() {
    return dishSet;
  }

  public void setDishSet(Set<Dish> dishSet) {
    this.dishSet = dishSet;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (this.id == null) {
      return false;
    }

    if (obj instanceof Restaurant && obj.getClass().equals(getClass())) {
      return this.id.equals(((Restaurant) obj).id);
    }

    return false;
  }

  @Override
  public String toString() {
    return "Restaurant{" + "restaurant='" + +'\'' + '}';
  }
}
