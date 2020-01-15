package es.uca.iw.sss.spring.backend.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Ship {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  @Column(unique = true, nullable = false)
  private String licensePlate = "";

  @Column(nullable = false)
  private String name = "";

  @Column(nullable = true)
  private String plane = "";

  @Column(nullable = true)
  private String legend = "";

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "ship", cascade = CascadeType.ALL)
  private Set<User> userSet = new HashSet<>();

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "ship", cascade = CascadeType.REMOVE)
  private Set<AdviceShip> adviceSet = new HashSet<>();

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "ship", cascade = CascadeType.REMOVE)
  private Set<Restaurant> restaurantSet = new HashSet<>();

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "ship", cascade = CascadeType.REMOVE)
  private Set<Scale> scaleSet = new HashSet<>();

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "ship", cascade = CascadeType.REMOVE)
  private Set<Tour> tourSet = new HashSet<>();

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "ship", cascade = CascadeType.REMOVE)
  private Set<Shop> shopSet = new HashSet<>();

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "ship", cascade = CascadeType.REMOVE)
  private Set<Spa> spaSet = new HashSet<>();

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "ship", cascade = CascadeType.REMOVE)
  private Set<Event> eventSet = new HashSet<>();

  public Ship() {}

  public Ship(String licensePlate, String name, String plane) {
    this.licensePlate = licensePlate;
    this.name = name;
    this.plane = plane;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLicensePlate() {
    return licensePlate;
  }

  public void setLicensePlate(String licensePlate) {
    this.licensePlate = licensePlate;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPlane() {
    return plane;
  }

  public void setPlane(String plane) {
    this.plane = plane;
  }

  public Set<User> getUserSet() {
    return userSet;
  }

  public void setUserSet(Set<User> userSet) {
    this.userSet = userSet;
  }

  public Set<Restaurant> getRestaurantSet() {
    return restaurantSet;
  }

  public void setRestaurantSet(Set<Restaurant> restaurantSet) {
    this.restaurantSet = restaurantSet;
  }

  public Set<AdviceShip> getAdviceSet() {
    return adviceSet;
  }

  public void setAdviceSet(Set<AdviceShip> adviceSet) {
    this.adviceSet = adviceSet;
  }

  public String getLegend() {
    return legend;
  }

  public void setLegend(String legend) {
    this.legend = legend;
  }

  public Set<Scale> getScaleSet() {
    return scaleSet;
  }

  public void setScaleSet(Set<Scale> scaleSet) {
    this.scaleSet = scaleSet;
  }

  public Set<Tour> getTourSet() {
    return tourSet;
  }

  public void setTourSet(Set<Tour> scaleSet) {
    this.tourSet = tourSet;
  }

  public Set<Shop> getShopSet() {
    return shopSet;
  }

  public void setShopSet(Set<Shop> shopSet) {
    this.shopSet = shopSet;
  }

  public Set<Spa> getSpaSet() {
    return spaSet;
  }

  public void setSpaSet(Set<Spa> spaSet) {
    this.spaSet = spaSet;
  }

  public Set<Event> getEventSet() {
    return eventSet;
  }

  public void setEventSet(Set<Event> eventSet) {
    this.eventSet = eventSet;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (this.id == null) {
      return false;
    }

    if (obj instanceof Ship && obj.getClass().equals(getClass())) {
      return this.id.equals(((Ship) obj).id);
    }

    return false;
  }

  @Override
  public String toString() {
    return "Ship{"
        + "licensePlate='"
        + licensePlate
        + '\''
        + ", name='"
        + name
        + '\''
        + ", plane='"
        + plane
        + '\''
        + '}';
  }

  @Override
  public Ship clone() throws CloneNotSupportedException {
    return (Ship) super.clone();
  }
}
